package com.czzy.core.entities.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 客户信息实体
 * @Date 2021/11/17 13:40
 * @Created by XD
 */
@Data
public class UserVo {

    private String id;

    private String username;

    private String password;

    private String nickName;

    private String role;

    private String headImg;

    private Date createTime;

    private Date updateTime;

}
