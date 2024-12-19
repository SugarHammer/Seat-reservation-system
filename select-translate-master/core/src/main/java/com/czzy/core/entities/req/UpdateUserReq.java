package com.czzy.core.entities.req;

import lombok.Data;


/**
 * @Description 修改个人信息请求
 * @Date 2021/11/17 13:35
 * @Created by XD
 */
@Data
public class UpdateUserReq {

    private String id;

    private String username;

    private String password;

    private String nickName;

    private String headImg;

}
