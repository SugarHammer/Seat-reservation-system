package com.czzy.core.entities.req;

import com.czzy.core.entities.bean.PageBean;
import lombok.Data;

/**
 * @Description 查询公告列表请求实体
 * @Date 2021/11/17 16:18
 * @Created by XD
 */
@Data
public class GetNoticeListReq extends PageBean {

    private String title;
}
