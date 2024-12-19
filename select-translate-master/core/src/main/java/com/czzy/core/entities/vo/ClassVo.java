package com.czzy.core.entities.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 自习室vo
 * @Date 2021/11/17 14:56
 * @Created by XD
 */
@Data
public class ClassVo {

    private String id;

    private String name;

    private Integer totalNum;

    private Integer checkedNum;

    private Boolean isOpen;

    private String rows;

    private Date openTime;

    private Date closeTime;


}
