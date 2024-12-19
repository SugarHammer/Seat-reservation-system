package com.czzy.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.req.AddNoticeReq;
import com.czzy.core.entities.req.DeleteNoticeReq;
import com.czzy.core.entities.req.GetNoticeListReq;
import com.czzy.core.entities.req.UpdateNoticeReq;
import com.czzy.core.entities.vo.NoticeVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-16
 */
public interface INoticeService extends IService<Notice> {

    /**
     * 增加一条公告
     */
    boolean add(AddNoticeReq req);

    /**
     * 批量删除公告
     */
    boolean deleted(DeleteNoticeReq req);

    /**
     * 修改公告
     */
    boolean updateOne(UpdateNoticeReq req);

    /**
     * 根据id获取公告
     */
    NoticeVo get(String id);

    /**
     * 获取公告列表
     */
    IPage<Notice> getList(GetNoticeListReq req);
}
