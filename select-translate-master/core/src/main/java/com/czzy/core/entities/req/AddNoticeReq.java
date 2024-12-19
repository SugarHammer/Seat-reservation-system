package com.czzy.core.entities.req;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 增加一条公告
 * @Date 2021/11/17 16:16
 * @Created by XD
 */
@Data
public class AddNoticeReq {

    private String title;

    private String content;

}
