package com.czzy.core.entities.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 公共vo
 * @Date 2021/11/17 16:18
 * @Created by XD
 */
@Data
public class NoticeVo {

    private String id;

    private String title;

    private String content;

    private String uid;

    private Integer readNum;

    private Date createTime;

    private Date updateTime;

    /**
     * 发布人名称
     */
    @TableField(exist = false)
    private String releaseName;
}
