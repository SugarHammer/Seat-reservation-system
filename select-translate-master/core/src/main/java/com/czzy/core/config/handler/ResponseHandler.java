package com.czzy.core.config.handler;

import com.czzy.core.entities.bean.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 规范接口返回数据
 */
@RestControllerAdvice(basePackages = "com.czzy.core")
public class ResponseHandler implements ResponseBodyAdvice<Object> {


    /**
     * 是否支持 advice 功能
     * @return true支持 false不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }


    /**
     * 处理 response 的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果为字符串类型，则转换为json
        if (o instanceof Result || o instanceof String) {
            return o;
        }
        return Result.success(o);
    }
}
