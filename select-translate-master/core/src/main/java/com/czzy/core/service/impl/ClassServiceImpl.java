package com.czzy.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czzy.core.entities.Class;
import com.czzy.core.entities.Seat;
import com.czzy.core.entities.req.AddClassReq;
import com.czzy.core.entities.req.DeleteClassReq;
import com.czzy.core.entities.req.GetClassListReq;
import com.czzy.core.entities.req.UpdateClassReq;
import com.czzy.core.entities.vo.ClassVo;
import com.czzy.core.exception.DefaultException;
import com.czzy.core.mapper.ClassMapper;
import com.czzy.core.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czzy.core.service.ISeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Autowired
    private ISeatService seatService;

    @Override
    @Transactional
    public boolean add(AddClassReq req) {
        Class aClass = new Class();
        String id = UUID.randomUUID().toString();
        aClass.setId(id);
        BeanUtils.copyProperties(req,aClass);
        if (!save(aClass)) throw new DefaultException("增加自习室失败");

        //增加对应的座位
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 0; i < aClass.getTotalNum(); i++) {
            Seat seat = new Seat();
            seat.setCid(id);
            seat.setState(0);
            seats.add(seat);
        }
        seatService.saveBatch(seats);

        return true;
    }

    @Override
    public boolean delete(DeleteClassReq req) {
        UpdateWrapper<Class> classUpdateWrapper = new UpdateWrapper<>();
        classUpdateWrapper.in("id",req.getIds()).set("deleted",1);
        if (!update(classUpdateWrapper)) throw new DefaultException("删除失败");

        return true;
    }

    @Override
    public boolean updateClass(UpdateClassReq req) {
        Class aClass = new Class();
        BeanUtils.copyProperties(req,aClass);
        if (!updateById(aClass)) throw new DefaultException("修改失败");
        return true;
    }

    @Override
    public ClassVo findById(String id) {
        ClassVo classVo = new ClassVo();
        Class dbClass = getById(id);
        BeanUtils.copyProperties(dbClass,classVo);
        return classVo;
    }

    @Override
    public IPage<Class> findList(GetClassListReq req) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        if (req.getName() != null && !req.getName().equals("")){
            classQueryWrapper.like("name",req.getName());
        }
        Page<Class> classPage = new Page<>(req.getCurrentPage(),req.getPageSize());
        return baseMapper.selectPage(classPage,classQueryWrapper);
    }
}
