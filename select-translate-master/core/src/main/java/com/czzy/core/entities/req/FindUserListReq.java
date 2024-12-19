package com.czzy.core.entities.req;

import com.czzy.core.entities.bean.PageBean;
import lombok.Data;

/**
 * @Description 查找客户列表请求实体
 * @Date 2021/11/17 13:42
 * @Created by XD
 */
@Data
public class FindUserListReq extends PageBean {

    private String username;

}
