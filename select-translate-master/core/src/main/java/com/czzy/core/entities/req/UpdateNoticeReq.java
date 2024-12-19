package com.czzy.core.entities.req;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description 修改公告请求实体
 * @Date 2021/11/17 16:17
 * @Created by XD
 */
@Data
public class UpdateNoticeReq {

    private String id;

    private String title;

    private String content;

}
