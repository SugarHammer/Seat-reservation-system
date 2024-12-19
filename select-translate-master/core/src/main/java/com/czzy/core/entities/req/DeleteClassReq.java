package com.czzy.core.entities.req;

import lombok.Data;

import java.util.List;

/**
 * @Description 删除自习室的请求实体
 * @Date 2021/11/16 18:55
 * @Created by XD
 */
@Data
public class DeleteClassReq {

    private List<String> ids;

}
