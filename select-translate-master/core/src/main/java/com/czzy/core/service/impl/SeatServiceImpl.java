package com.czzy.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czzy.core.entities.Seat;
import com.czzy.core.entities.vo.SeatVo;
import com.czzy.core.mapper.SeatMapper;
import com.czzy.core.service.ISeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-25
 */
@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, Seat> implements ISeatService {

    @Override
    public List<SeatVo> getList(String id) {
        ArrayList<SeatVo> seatVos = new ArrayList<>();

        QueryWrapper<Seat> seatQueryWrapper = new QueryWrapper<>();
        seatQueryWrapper.eq("cid",id);
        List<Seat> list = list(seatQueryWrapper);
        for (Seat seat : list) {
            SeatVo seatVo = new SeatVo();
            BeanUtils.copyProperties(seat,seatVo);
            seatVos.add(seatVo);
        }

        return seatVos;
    }
}
