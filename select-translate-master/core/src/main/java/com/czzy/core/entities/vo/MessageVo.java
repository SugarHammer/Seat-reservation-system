package com.czzy.core.entities.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 根据id查询留言内容
 * @Date 2021/11/17 15:24
 * @Created by XD
 */
@Data
public class MessageVo {

    private String id;

    private String uid;

    private String content;

    private String replay;

    private String rid;

    private Date replayTime;

    private Date createTime;

    private Date updateTime;

    /**
     * 回复人昵称名称
     */
    @TableField(exist = false)
    private String replayName;

}
