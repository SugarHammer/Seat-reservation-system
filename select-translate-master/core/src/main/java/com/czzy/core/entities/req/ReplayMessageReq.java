package com.czzy.core.entities.req;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @Description 管理员回复留言请求
 * @Date 2021/11/17 15:20
 * @Created by XD
 */
@Data
public class ReplayMessageReq {

    private String id;

    private String replay;

}
