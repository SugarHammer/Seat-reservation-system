package com.czzy.core.entities.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Date 2021/11/25 10:03
 * @Created by XD
 */
@Data
public class SeatVo {

    private String id;

    @ApiModelProperty(value = "图书馆教室id")
    private String cid;

    @ApiModelProperty(value = "座位状态(0未选,1已选)")
    private Integer state;
}
