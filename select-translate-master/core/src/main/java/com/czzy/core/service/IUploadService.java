package com.czzy.core.service;

import com.czzy.core.entities.bean.Result;

/**
 * @Description 上传下载资源服务层
 * @Date 2021/11/17 13:27
 * @Created by XD
 */
public interface IUploadService {

    /**
     * 上传图片
     * @return 图片地址
     */
    Result upload();

}
