package com.czzy.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description 验证码接口
 * @Date 2021/11/16 11:04
 * @Created by XD
 */
@RestController
@Api(tags = "验证码接口")
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/get")
    @ApiOperation("获取验证码")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        //保存到redis,60秒过期
        String captchaId = request.getParameter("cid");
        redisTemplate.opsForValue().set("captcha::"+captchaId,lineCaptcha.getCode(),60, TimeUnit.SECONDS);

        //图形验证码写出
        try {
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
