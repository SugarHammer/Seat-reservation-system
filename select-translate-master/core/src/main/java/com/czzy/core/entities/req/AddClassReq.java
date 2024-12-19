package com.czzy.core.entities.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 增加自习室
 * @Date 2021/11/16 17:20
 * @Created by XD
 */
@Data
public class AddClassReq {

    @NotNull(message = "自习室名称不能为空")
    @NotBlank(message = "自习室名称不能为空")
    private String name;

    @NotNull(message = "容量不能为空")
    private Integer totalNum;

    @NotNull(message = "是否开启不能为空")
    private Boolean isOpen;

    @NotNull(message = "排数不能为空")
    private String rows;

    @NotNull(message = "开启时间不能为空")
    private Date openTime;

    @NotNull(message = "关闭时间不能为空")
    private Date closeTime;

}
