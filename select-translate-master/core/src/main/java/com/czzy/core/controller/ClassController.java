package com.czzy.core.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.Class;
import com.czzy.core.entities.req.AddClassReq;
import com.czzy.core.entities.req.DeleteClassReq;
import com.czzy.core.entities.req.GetClassListReq;
import com.czzy.core.entities.req.UpdateClassReq;
import com.czzy.core.entities.vo.ClassVo;
import com.czzy.core.service.IClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/admin/class")
@Api(tags = "自习室相关接口")
public class ClassController {

    @Autowired
    private IClassService classService;

    @PostMapping
    @ApiOperation("增加自习室")
    @SaCheckRole("admin")
    public boolean add(@RequestBody @Validated AddClassReq req){
        return classService.add(req);
    }

    @DeleteMapping("/delete")
    @ApiOperation("批量删除自习室")
    @SaCheckRole("admin")
    public boolean del(@RequestBody DeleteClassReq req){
        return classService.delete(req);
    }

    @PutMapping("/update")
    @ApiOperation("修改自习室")
    @SaCheckRole("admin")
    public boolean update(@RequestBody @Validated UpdateClassReq req){
        return classService.updateClass(req);
    }

    @PutMapping("/find/{id}")
    @ApiOperation("根据id查询自习室")
    public ClassVo update(@PathVariable String id){
        return classService.findById(id);
    }

    @PutMapping("/find/admin/list")
    @SaCheckRole("admin")
    @ApiOperation("查询自习室列表")
    public IPage<Class> findListAdmin(@RequestBody GetClassListReq req){
        return classService.findList(req);
    }

    @PutMapping("/find/list")
    @ApiOperation("查询自习室列表")
    public IPage<Class> findList(@RequestBody GetClassListReq req){
        return classService.findList(req);
    }
}
