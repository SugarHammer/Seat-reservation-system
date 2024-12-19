package com.czzy.core.entities.req;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Date 2021/11/17 15:22
 * @Created by XD
 */
@Data
public class DeletedBatchReq {

    private List<String> ids;
}
