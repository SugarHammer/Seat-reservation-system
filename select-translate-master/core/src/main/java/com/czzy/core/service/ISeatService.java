package com.czzy.core.service;

import com.czzy.core.entities.Seat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czzy.core.entities.vo.SeatVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-11-25
 */
public interface ISeatService extends IService<Seat> {

    /**
     * 获取自习室的座位集合
     */
    List<SeatVo> getList(String id);
}
