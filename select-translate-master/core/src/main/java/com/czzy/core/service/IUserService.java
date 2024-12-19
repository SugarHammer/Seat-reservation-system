package com.czzy.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.bean.Result;
import com.czzy.core.entities.req.*;
import com.czzy.core.entities.vo.LoginVo;
import com.czzy.core.entities.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
public interface IUserService extends IService<User> {

    /**
     * 客户登录
     */
    LoginVo login(UserLoginReq req);

    /**
     * 客户注册
     */
    boolean register(UserRegisterReq req);

    /**
     * 批量删除客户
     */
    boolean deleteByIds(DeleteUserReq req);

    /**
     * 修改客户信息
     */
    boolean updateUser(UpdateUserReq req);

    /**
     * 根据id查询客户信息
     */
    UserVo findByid(String id);

    /**
     * 查找用户列表
     */
    IPage<User> findList(FindUserListReq req);

    /**
     * 用户退出登录
     */
    boolean logout();

    /**
     * 查看当前客户的信息
     */
    UserVo findCurrentInfo();

}
