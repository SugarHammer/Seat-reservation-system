package com.czzy.core.entities.enumbean;

/**
 * @Description 返回枚举类
 * @Date 2021/11/17 11:08
 * @Created by XD
 */
public enum ResultEnum {

    SUCCESS(2000, "请求成功"),
    SUCCESS_ADD(2001, "添加成功"),
    SUCCESS_DELETE(2002, "删除成功"),
    SUCCESS_UPDATE(2003, "修改成功"),
    SUCCESS_SELECT(2004, "查询成功"),
    SUCCESS_UPLOAD(20005,"上传成功"),
    FAIL_ADD(5001, "添加失败"),
    FAIL_DELETE(5002, "删除失败"),
    FAIL_UPDATE(5003, "修改失败"),
    FAIL_SELECT(5004, "查询失败"),
    FAIL_UPLOAD(5005, "上传失败"),
    FAIL_CAPTCHA(5006, "验证码输入有误"),
    FAIL_SERVER(5000, "服务器发生错误");

    ResultEnum() {
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
