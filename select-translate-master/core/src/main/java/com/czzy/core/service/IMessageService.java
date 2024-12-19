package com.czzy.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.req.DeletedBatchReq;
import com.czzy.core.entities.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.req.AddMessageReq;
import com.czzy.core.entities.req.GetMessageListReq;
import com.czzy.core.entities.req.ReplayMessageReq;
import com.czzy.core.entities.vo.MessageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
public interface IMessageService extends IService<Message> {

    /**
     * 增加一条留言
     */
    boolean createMessage(AddMessageReq req);

    /**
     * 管理员回复留言
     */
    boolean replayMessage(ReplayMessageReq req);

    /**
     * 删除一条留言
     */
    boolean deletedMessage(String id);

    /**
     * 批量删除留言
     */
    boolean batchMessage(DeletedBatchReq req);

    /**
     * 获取留言列表
     */
    IPage<Message> getMessageList(GetMessageListReq req);

    /**
     * 根据id查询留言
     */
    MessageVo getMessage(String id);
}
