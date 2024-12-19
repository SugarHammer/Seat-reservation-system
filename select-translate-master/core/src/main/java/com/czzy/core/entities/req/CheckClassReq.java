package com.czzy.core.entities.req;

import lombok.Data;


/**
 * @Description 预约自习教室
 * @Date 2021/11/17 15:47
 * @Created by XD
 */
@Data
public class CheckClassReq {

    private String classId;

    private String seatNum;

}
