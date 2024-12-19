package com.czzy.core.entities.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @Description 分页类，所有带分页的请求都必须继承该类
 * @Date 2021/11/17 13:43
 * @Created by XD
 */
@Data
public class PageBean {

    private Integer currentPage;
    private Integer pageSize;

}
