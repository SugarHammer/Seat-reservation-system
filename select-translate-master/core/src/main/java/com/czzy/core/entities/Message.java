package com.czzy.core.entities;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String uid;

    private String content;

    private String replay;

    private String rid;

    private Date replayTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    /**
     * 回复人昵称名称
     */
    @TableField(exist = false)
    private String replayName;

    /**
     * 留言昵称名称
     */
    @TableField(exist = false)
    private String username;
}
