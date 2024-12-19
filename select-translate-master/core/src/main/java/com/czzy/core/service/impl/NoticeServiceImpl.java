package com.czzy.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.UpdateById;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.Notice;
import com.czzy.core.entities.req.AddNoticeReq;
import com.czzy.core.entities.req.DeleteNoticeReq;
import com.czzy.core.entities.req.GetNoticeListReq;
import com.czzy.core.entities.req.UpdateNoticeReq;
import com.czzy.core.entities.vo.NoticeVo;
import com.czzy.core.exception.DefaultException;
import com.czzy.core.mapper.NoticeMapper;
import com.czzy.core.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czzy.core.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-16
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private IUserService userService;

    @Override
    public boolean add(AddNoticeReq req) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(req,notice);
        notice.setUid(StpUtil.getLoginIdAsString());
        if (!save(notice)) throw new DefaultException("发布公告失败");
        return true;
    }

    @Override
    public boolean deleted(DeleteNoticeReq req) {
        UpdateWrapper<Notice> noticeUpdateWrapper = new UpdateWrapper<>();
        noticeUpdateWrapper.in("id",req.getIds()).set("deleted",1);
        if (!update(noticeUpdateWrapper)) throw new DefaultException("删除公告失败");
        return true;
    }

    @Override
    public boolean updateOne(UpdateNoticeReq req) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(req,notice);
        if (!updateById(notice)) throw new DefaultException("修改失败");
        return true;
    }

    @Override
    public NoticeVo get(String id) {
        NoticeVo noticeVo = new NoticeVo();

        Notice notice = getById(id);
        BeanUtils.copyProperties(notice,noticeVo);
        noticeVo.setReleaseName(userService.getById(notice.getUid()).getNickName());
        return noticeVo;
    }

    @Override
    public IPage<Notice> getList(GetNoticeListReq req) {
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        if (req.getTitle() != null && !req.getTitle().equals("")){
            noticeQueryWrapper.like("title",req.getTitle());
        }
        Page<Notice> noticePage = new Page<>(req.getCurrentPage(), req.getPageSize());

        return baseMapper.selectPage(noticePage,noticeQueryWrapper);
    }
}
