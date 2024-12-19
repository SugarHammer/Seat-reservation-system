package com.czzy.core.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.User;
import com.czzy.core.entities.req.*;
import com.czzy.core.entities.vo.LoginVo;
import com.czzy.core.entities.vo.UserVo;
import com.czzy.core.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public LoginVo login(@RequestBody @Validated UserLoginReq req) {
        return userService.login(req);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public boolean register(@RequestBody @Validated UserRegisterReq req) {
        return userService.register(req);
    }

    @DeleteMapping("/deleted")
    @SaCheckRole("admin")
    @ApiOperation("批量删除客户")
    public boolean deleted(@RequestBody DeleteUserReq req) {
        return userService.deleteByIds(req);
    }

    @PutMapping("/update")
    @SaCheckRole("admin")
    @ApiOperation("修改客户个人信息")
    public boolean update(@RequestBody UpdateUserReq req) {
        return userService.updateUser(req);
    }

    @GetMapping("/find/{id}")
    @ApiOperation("根据id查看客户个人信息")
    public UserVo findId(@PathVariable String id) {
        return userService.findByid(id);
    }

    @GetMapping("/find/current/info")
    @ApiOperation("查看当前客户信息")
    public UserVo findCurrentInfo() {
        return userService.findCurrentInfo();
    }


    @GetMapping("/logout")
    @ApiOperation("退出")
    public boolean logout() {
        return userService.logout();
    }

    @PostMapping("/find/list")
    @ApiOperation("查看用户列表")
    public IPage<User> findList(@RequestBody FindUserListReq req) {
        return userService.findList(req);
    }

}
