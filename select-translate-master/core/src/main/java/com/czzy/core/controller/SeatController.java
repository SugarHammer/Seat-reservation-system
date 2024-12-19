package com.czzy.core.controller;


import com.czzy.core.entities.vo.SeatVo;
import com.czzy.core.service.ISeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat")
@Api(tags = "自习室座位接口")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    @GetMapping("/get/list/{id}")
    @ApiOperation("获取自习室下的座位集合")
    public List<SeatVo> getList(@PathVariable String id){
       return seatService.getList(id);
    }

}
