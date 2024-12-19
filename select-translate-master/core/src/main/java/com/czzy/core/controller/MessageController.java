package com.czzy.core.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czzy.core.entities.Message;
import com.czzy.core.entities.req.AddMessageReq;
import com.czzy.core.entities.req.DeletedBatchReq;
import com.czzy.core.entities.req.GetMessageListReq;
import com.czzy.core.entities.req.ReplayMessageReq;
import com.czzy.core.entities.vo.MessageVo;
import com.czzy.core.service.IMessageService;
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
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/message")
@Api(tags = "留言接口")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping
    @ApiOperation("增加留言")
    public boolean createMessage(@RequestBody AddMessageReq req){
        return messageService.createMessage(req);
    }

    @PostMapping("/replay")
    @SaCheckRole("admin")
    @ApiOperation("管理员回复留言")
    public boolean replayMessage(@RequestBody ReplayMessageReq req){
        return messageService.replayMessage(req);
    }

    @DeleteMapping("/delete/{id}")
    @SaCheckRole("admin")
    @ApiOperation("删除一条留言")
    public boolean deletedMessage(@PathVariable String id){
        return messageService.deletedMessage(id);
    }

    @DeleteMapping("/delete/batch")
    @SaCheckRole("admin")
    @ApiOperation("管理员批量删除留言")
    public boolean deletedBatchMessage(@RequestBody DeletedBatchReq req){
        return messageService.batchMessage(req);
    }

    @PostMapping("/get/list")
    @ApiOperation("查询留言列表")
    public IPage<Message> getMessageList(@RequestBody GetMessageListReq req){
        return messageService.getMessageList(req);
    }

    @GetMapping("/get/one/{id}")
    @ApiOperation("根据id查询留言内容")
    public MessageVo getMessage(@PathVariable String id){
        return messageService.getMessage(id);
    }


}
