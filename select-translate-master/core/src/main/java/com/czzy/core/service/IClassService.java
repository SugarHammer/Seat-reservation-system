package com.czzy.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.req.AddClassReq;
import com.czzy.core.entities.req.DeleteClassReq;
import com.czzy.core.entities.req.GetClassListReq;
import com.czzy.core.entities.req.UpdateClassReq;
import com.czzy.core.entities.vo.ClassVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
public interface IClassService extends IService<Class> {


    /**
     * 增加自习室
     */
    boolean add(AddClassReq req);

    /**
     * 批量删除自习室
     */
    boolean delete(DeleteClassReq req);

    /**
     * 修改自习室
     */
    boolean updateClass(UpdateClassReq req);

    /**
     * 根据id查询自习室
     */
    ClassVo findById(String id);

    /**
     * 自习室列表
     * @param req
     * @return
     */
    IPage<Class> findList(GetClassListReq req);
}
