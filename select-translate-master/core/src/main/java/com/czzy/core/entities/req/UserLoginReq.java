package com.czzy.core.entities.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 用户登录请求实体
 * @Date 2021/11/16 10:57
 * @Created by XD
 */
@Data
public class UserLoginReq {

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "验证码不能为空")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    private String captchaId;

    private boolean rememberMe;

}
