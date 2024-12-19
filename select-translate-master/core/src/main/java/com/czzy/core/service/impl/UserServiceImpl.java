package com.czzy.core.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.User;
import com.czzy.core.entities.bean.Result;
import com.czzy.core.entities.enumbean.ResultEnum;
import com.czzy.core.entities.req.*;
import com.czzy.core.entities.vo.LoginVo;
import com.czzy.core.entities.vo.UserVo;
import com.czzy.core.exception.DefaultException;
import com.czzy.core.mapper.UserMapper;
import com.czzy.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginVo login(UserLoginReq req) {

        LoginVo loginVo = new LoginVo();
        String captchaValue = (String) redisTemplate.opsForValue().get("captcha::" + req.getCaptchaId());
        if (captchaValue == null || !captchaValue.equals(req.getCaptcha())) throw new DefaultException("验证码输入错误");

        //销毁验证码
        redisTemplate.delete("captcha::" + req.getCaptchaId());

        //判断用户名密码是否正确
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", req.getUsername()).
                eq("password", SaBase64Util.encode(req.getPassword()));
        User user = getOne(userQueryWrapper);
        if (user == null) throw new DefaultException("用户名或密码错误");

        //用户登录成功
        StpUtil.login(user.getId(), req.isRememberMe());
        StpUtil.getSession().set(StpUtil.getTokenName(), user);

        loginVo.setRole(user.getRole());
        loginVo.setToken(StpUtil.getTokenValue());
        loginVo.setHeadImg(user.getHeadImg());
        return loginVo;
    }

    @Override
    public boolean register(UserRegisterReq req) {
        //判断两次密码是否输入正确
        if (!req.getPassword().equals(req.getCheckPassword())) throw new DefaultException("两次密码输入不一致");

        //判断账号是否被注册
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", req.getUsername());
        User dbUser = getOne(userQueryWrapper);
        if (dbUser != null) throw new DefaultException("账号已被注册");

        //允许客户注册
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setHeadImg("https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoM6WDpQlUMDAxqvq3iaXsx9OlqFKAcWZ7w5hbLb5fKqvcWYPCxTtydelytE6740jicEFAP3iaCAU6Ag/132");
        user.setPassword(SaBase64Util.encode(user.getPassword()));
        user.setRole("user");
        if (!save(user)) throw new DefaultException("账号注册失败，系统原因");

        return true;
    }

    @Override
    @Transactional
    public boolean deleteByIds(DeleteUserReq req) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.in("id", req.getIds()).set("deleted", 1);
        if (update(userUpdateWrapper)) return true;
        throw new DefaultException("删除失败");
    }

    @Override
    public boolean updateUser(UpdateUserReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        if (updateById(user)) return true;

        throw new DefaultException("修改失败");
    }

    @Override
    public UserVo findByid(String id) {
        UserVo userVo = new UserVo();
        User user = getById(id);
        if (user == null) throw new DefaultException("查询客户信息失败");

        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public IPage<User> findList(FindUserListReq req) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (req.getUsername() != null && !req.getUsername().equals("")){
            userQueryWrapper.like("username",req.getUsername());
        }

        Page<User> userPage = new Page<>(req.getCurrentPage(),req.getPageSize());
        return baseMapper.selectPage(userPage, userQueryWrapper);
    }

    @Override
    public boolean logout() {
        StpUtil.logout();
        return false;
    }

    @Override
    public UserVo findCurrentInfo() {
        UserVo userVo = new UserVo();
        String id = null;
        try {
            id = StpUtil.getLoginIdAsString();
        } catch (NotLoginException e) {
            e.printStackTrace();
        }
        User user = getById(id);
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

}
