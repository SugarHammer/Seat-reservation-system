package com.czzy.core.entities.vo;

import lombok.Data;

/**
 * @Description 登录返回的内容
 * @Date 2021/11/18 14:45
 * @Created by XD
 */
@Data
public class LoginVo {

    private String token;
    private String role;
    private String headImg;
}
