package com.czzy.core.entities.req;

import com.czzy.core.entities.bean.PageBean;
import lombok.Data;

/**
 * @Description 获取自习室列表请求
 * @Date 2021/11/17 14:59
 * @Created by XD
 */
@Data
public class GetClassListReq extends PageBean {

    private String name;

}
