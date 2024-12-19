package com.czzy.core.controller;

import com.czzy.core.entities.bean.Result;
import com.czzy.core.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 上传、下载资源接口
 * @Date 2021/11/17 13:24
 * @Created by XD
 */
//@RestController
@RequestMapping("/upload")
@Api(tags = "上传、下载资源接口")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping
    @ApiOperation("上传图片")
    public Result upload(MultipartFile file){
        return uploadService.upload();
    }


}
