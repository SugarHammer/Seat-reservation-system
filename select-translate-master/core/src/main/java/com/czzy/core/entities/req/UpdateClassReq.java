package com.czzy.core.entities.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description 修改自习室请求实体
 * @Date 2021/11/16 17:15
 * @Created by XD
 */
@Data
public class UpdateClassReq{

    private String id;

    private String name;

    private Integer totalNum;

    private Integer checkedNum;

    private Boolean isOpen;

    private String rows;

    private Date openTime;

    private Date closeTime;

}
