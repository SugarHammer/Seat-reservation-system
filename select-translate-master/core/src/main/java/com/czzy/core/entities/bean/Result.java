package com.czzy.core.entities.bean;

import com.czzy.core.entities.enumbean.ResultEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Description 同意返回实体
 * @Date 2021/11/17 11:06
 * @Created by XD
 */
@Data
@Component
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Result(ResultEnum resultEnum,T obj){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        data = obj;
    }

    /**
     * 一般成功响应
     */
    public static Result success(Object obj){
        return new Result(ResultEnum.SUCCESS,obj);
    }


}
