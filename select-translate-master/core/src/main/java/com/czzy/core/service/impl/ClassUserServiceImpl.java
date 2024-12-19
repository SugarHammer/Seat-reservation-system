package com.czzy.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.czzy.core.entities.Class;
import com.czzy.core.entities.ClassUser;
import com.czzy.core.entities.Seat;
import com.czzy.core.entities.req.CheckClassReq;
import com.czzy.core.entities.vo.ClassUserVo;
import com.czzy.core.exception.DefaultException;
import com.czzy.core.mapper.ClassMapper;
import com.czzy.core.mapper.ClassUserMapper;
import com.czzy.core.service.IClassService;
import com.czzy.core.service.IClassUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czzy.core.service.ISeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Service
public class ClassUserServiceImpl extends ServiceImpl<ClassUserMapper, ClassUser> implements IClassUserService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ISeatService seatService;

    @Override
    public boolean checked(String id) {
        QueryWrapper<ClassUser> classUserQueryWrapper = new QueryWrapper<>();
        classUserQueryWrapper.eq("user_id", StpUtil.getLoginIdAsString()).eq("class_id",id);
        return getOne(classUserQueryWrapper) != null;
    }

    @Override
    @Transactional
    public boolean check(CheckClassReq req) {
        ClassUser classUser = new ClassUser();
        BeanUtils.copyProperties(req,classUser);
        classUser.setUserId(StpUtil.getLoginIdAsString());

        //判断该座位是否被预约
        QueryWrapper<Seat> seatQueryWrapper = new QueryWrapper<>();
        seatQueryWrapper.eq("id",req.getSeatNum())
                .eq("cid",req.getClassId());
        Seat one = seatService.getOne(seatQueryWrapper);
        if (one.getState() == 1) throw new DefaultException("该座位已被预约");

        //查看客户是否已经在该自习室预约过
        QueryWrapper<ClassUser> classUserQueryWrapper = new QueryWrapper<>();
        classUserQueryWrapper.eq("class_id",req.getClassId())
                .eq("user_id",StpUtil.getLoginIdAsString());
        ClassUser classUserDb = getOne(classUserQueryWrapper);
        if (classUserDb != null) throw new DefaultException("该自习室一人只可以预约一次~");

        //将该座位标记已预约
        UpdateWrapper<Seat> seatUpdateWrapper = new UpdateWrapper<>();
        seatUpdateWrapper.eq("id",req.getSeatNum()).set("state",1);
        if (!seatService.update(seatUpdateWrapper)) throw new DefaultException("标记座位为已预约失败");

        //增加自习室的已选人数
        if (classMapper.incrementCheckNum(req.getClassId()) <= 0) throw new DefaultException("预约失败");

        //生成预约记录
        if (!save(classUser)) throw new DefaultException("预约失败");
        return true;
    }

    @Override
    @Transactional
    public boolean cancel(String id) {
        ClassUser classUser = getById(id);

        //删除预约记录
        UpdateWrapper<ClassUser> classUserUpdateWrapper = new UpdateWrapper<>();
        classUserUpdateWrapper.eq("user_id",StpUtil.getLoginIdAsString())
                .eq("class_id",classUser.getClassId()).set("deleted",1);
        if (!update(classUserUpdateWrapper)) throw new DefaultException("取消预约失败");

        //减少自习室的已选人数
        if (classMapper.decrementCheckNum(classUser.getClassId()) <= 0) throw new DefaultException("取消预约失败");

        //将座位状态设置无人
        UpdateWrapper<Seat> seatUpdateWrapper = new UpdateWrapper<>();
        seatUpdateWrapper.eq("id",classUser.getSeatNum()).set("state",0);
        if (!seatService.update(seatUpdateWrapper)) throw new DefaultException("取消预约失败");
        return true;
    }

    @Override
    public List<ClassUserVo> getList() {
        ArrayList<ClassUserVo> classUserVos = new ArrayList<>();

        String id = StpUtil.getLoginIdAsString();
        QueryWrapper<ClassUser> classUserQueryWrapper = new QueryWrapper<>();
        classUserQueryWrapper.eq("user_id",id);
        List<ClassUser> list = list(classUserQueryWrapper);
        for (ClassUser classUser : list) {
            ClassUserVo classUserVo = new ClassUserVo();
            //查询自习室名称
            QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
            classQueryWrapper.eq("id",classUser.getClassId());
            Class aClass = classMapper.selectOne(classQueryWrapper);
            classUserVo.setClassName(aClass.getName());
            classUserVo.setSeatNum(classUser.getSeatNum());
            classUserVo.setCreateTime(classUser.getCreateTime());
            classUserVo.setId(classUser.getId());
            classUserVos.add(classUserVo);
        }
        return classUserVos;
    }
}
