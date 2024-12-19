package com.czzy.core.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.Notice;
import com.czzy.core.entities.req.AddNoticeReq;
import com.czzy.core.entities.req.DeleteNoticeReq;
import com.czzy.core.entities.req.GetNoticeListReq;
import com.czzy.core.entities.req.UpdateNoticeReq;
import com.czzy.core.entities.vo.NoticeVo;
import com.czzy.core.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-16
 */
@RestController
@RequestMapping("/notice")
@Api(tags = "公告通知接口")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @PostMapping
    @ApiOperation("增加一条公告")
    @SaCheckRole("admin")
    public boolean add(@RequestBody AddNoticeReq req){
        return noticeService.add(req);
    }

    @DeleteMapping("/delete")
    @SaCheckRole("admin")
    @ApiOperation("批量删除")
    public boolean delete(@RequestBody DeleteNoticeReq req){
        return noticeService.deleted(req);
    }

    @PutMapping("/update")
    @SaCheckRole("admin")
    @ApiOperation("修改公告")
    public boolean update(@RequestBody UpdateNoticeReq req){
        return noticeService.updateOne(req);
    }

    @GetMapping("/get/one/{id}")
    @ApiOperation("根据id查询公告")
    public NoticeVo getOne(@PathVariable String id){
        return noticeService.get(id);
    }

    @PostMapping("/get/list")
    @ApiOperation("查询公告列表")
    public IPage<Notice> getList(@RequestBody GetNoticeListReq req){
        return noticeService.getList(req);
    }

}
