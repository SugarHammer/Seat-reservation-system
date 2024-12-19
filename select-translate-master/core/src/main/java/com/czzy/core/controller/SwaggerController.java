package com.czzy.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description swagger相关的api
 * @Date 2021/11/17 11:38
 * @Created by XD
 */
@Controller
public class SwaggerController {

    @RequestMapping("/")
    public String customerIndex() {
        return "redirect:/doc.html";
    }

}
