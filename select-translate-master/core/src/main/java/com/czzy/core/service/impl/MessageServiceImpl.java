package com.czzy.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.req.DeletedBatchReq;
import com.czzy.core.entities.Message;
import com.czzy.core.entities.User;
import com.czzy.core.entities.req.AddMessageReq;
import com.czzy.core.entities.req.GetMessageListReq;
import com.czzy.core.entities.req.ReplayMessageReq;
import com.czzy.core.entities.vo.MessageVo;
import com.czzy.core.exception.DefaultException;
import com.czzy.core.mapper.MessageMapper;
import com.czzy.core.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czzy.core.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private IUserService userService;

    @Override
    public boolean createMessage(AddMessageReq req) {
        Message message = new Message();
        BeanUtils.copyProperties(req,message);
        message.setUid(StpUtil.getLoginIdAsString());
        if (!save(message)) throw new DefaultException("留言失败");
        return true;
    }

    @Override
    public boolean replayMessage(ReplayMessageReq req) {
        Message message = new Message();
        BeanUtils.copyProperties(req,message);

        User user = (User) StpUtil.getSession().get(StpUtil.getTokenName());
        message.setReplayName(user.getNickName());
        message.setRid(user.getId());
        if (!updateById(message)) throw new DefaultException("回复留言失败");

        return true;
    }

    @Override
    public boolean deletedMessage(String id) {
        UpdateWrapper<Message> messageUpdateWrapper = new UpdateWrapper<>();
        messageUpdateWrapper.eq("id",id).set("deleted",1);
        if (!update(messageUpdateWrapper)) throw new DefaultException("删除失败");

        return true;
    }

    @Override
    public boolean batchMessage(DeletedBatchReq req) {
        UpdateWrapper<Message> messageUpdateWrapper = new UpdateWrapper<>();
        messageUpdateWrapper.in("id",req.getIds()).set("deleted",1);
        if (!update(messageUpdateWrapper)) throw new DefaultException("批量删除失败");
        return true;
    }

    @Override
    public IPage<Message> getMessageList(GetMessageListReq req) {
        Page<Message> page = new Page<>(req.getCurrentPage(), req.getPageSize());
        Page<Message> messagePage = baseMapper.selectPage(page, null);

        List<Message> messages = messagePage.getRecords();
        for (Message message : messages) {
            message.setUsername(userService.getById(message.getUid()).getUsername());
            if (!StrUtil.isEmpty(message.getRid())){
                User user = userService.getById(message.getRid());
                message.setReplayName(user.getNickName());
            }
        }
        return messagePage;
    }

    @Override
    public MessageVo getMessage(String id) {
        MessageVo messageVo = new MessageVo();

        Message message = getById(id);
        BeanUtils.copyProperties(message,messageVo);
        return messageVo;
    }
}
