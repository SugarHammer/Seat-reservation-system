package com.czzy.core.entities.req;

import lombok.Data;

import java.util.List;

/**
 * @Description 批量删除
 * @Date 2021/11/17 16:17
 * @Created by XD
 */
@Data
public class DeleteNoticeReq {
    private List<String> ids;
}
