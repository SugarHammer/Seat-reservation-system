package com.czzy.core.entities.req;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 客户注册
 * @Date 2021/11/16 11:29
 * @Created by XD
 */
@Data
public class UserRegisterReq {

    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull(message = "昵称不能为空")
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "确认密码不能为空")
    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;

}
