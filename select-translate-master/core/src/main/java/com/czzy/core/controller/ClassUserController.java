package com.czzy.core.controller;


import com.czzy.core.entities.req.CheckClassReq;
import com.czzy.core.entities.vo.ClassUserVo;
import com.czzy.core.service.IClassUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/class-user")
@Api(tags = "自习室关联用户接口")
public class ClassUserController {

    @Autowired
    private IClassUserService classUserService;

    @GetMapping("/is/checked/{id}")
    @ApiOperation("判断该用户是否预约了该自习室")
    public boolean checked(@PathVariable String id){
        return classUserService.checked(id);
    }

    @PostMapping("/check")
    @ApiOperation("学生预约自习室")
    public boolean check(@RequestBody CheckClassReq req){
        return classUserService.check(req);
    }

    @GetMapping("/cancel/{id}")
    @ApiOperation("学生取消预约自习室")
    public boolean cancel(@PathVariable String id){
        return classUserService.cancel(id);
    }

    @PostMapping("/list")
    @ApiOperation("获取学生预约列表")
    public List<ClassUserVo> list(){
        return classUserService.getList();
    }
}
