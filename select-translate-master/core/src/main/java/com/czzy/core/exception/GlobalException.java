package com.czzy.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.czzy.core.entities.bean.Result;
import com.czzy.core.entities.enumbean.ResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {

    // 全局异常拦截（拦截项目中的所有异常）
    @ExceptionHandler
    public Result handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 不同异常返回不同状态码
        String res = null;
        if (e instanceof NotLoginException) {    // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            res = ee.getMessage();
            Result result = new Result();
            result.setCode(5001);
            result.setMsg(res);
            return result;
        } else if (e instanceof NotRoleException) {        // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            res = "无此角色：" + ee.getRole();
            Result result = new Result();
            result.setCode(5002);
            result.setMsg(res);
            return result;
        } else if (e instanceof NotPermissionException) {    // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            res = "无此权限：" + ee.getCode();
            Result result = new Result();
            result.setCode(5002);
            result.setMsg(res);
            return result;
        } else if (e instanceof DisableLoginException) {    // 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            res = "账号被封禁：" + ee.getDisableTime() + "秒后解封";
        } else if (e instanceof DefaultException) {        //自定义异常
            Result result = new Result();
            result.setCode(20000);
            result.setMsg(e.getMessage());
            return result;
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = ex.getBindingResult().getAllErrors();
            Result result = new Result();
            result.setCode(20000);
            result.setMsg(errors.get(0).getDefaultMessage());
            return result;
        } else {
            res = "服务器异常" + e.getMessage();
        }

        // 返回给前端
        Result result = new Result();
        result.setCode(5000);
        result.setMsg(res);
        return result;
    }


    /**
     * 参数校验获取错误提示信息
     */
    private String formatError(List<ObjectError> errors) {
        StringBuilder builder = new StringBuilder();
        errors.forEach(error -> builder.append(error.getDefaultMessage()));
        return builder.toString();
    }
}
