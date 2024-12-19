package com.czzy.core.service;

import com.czzy.core.entities.ClassUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.req.CheckClassReq;
import com.czzy.core.entities.vo.ClassUserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
public interface IClassUserService extends IService<ClassUser> {

    /**
     * 判断当前用户是否预约了该自习室
     */
    boolean checked(String id);

    /**
     * 学生预约自习室
     */
    boolean check(CheckClassReq req);

    /**
     * 取消预约自习室
     */
    boolean cancel(String id);

    /**
     * 获取当前登录的学生预约列表
     */
    List<ClassUserVo> getList();

}
