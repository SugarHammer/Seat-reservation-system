package com.czzy.core.entities.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description 分页对象
 * @Date 2021/11/16 14:49
 * @Created by XD
 */
@Data
public class PageBean<T> {
    private Integer code;
    private List<T> data;
    private Integer count;
    private String msg;
}
