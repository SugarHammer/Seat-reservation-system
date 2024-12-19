package com.czzy.core.entities.req;

import lombok.Data;

import java.util.List;

/**
 * @Description 批量删除客户
 * @Date 2021/11/17 13:29
 * @Created by XD
 */
@Data
public class DeleteUserReq {

    private List<String> ids;
}
