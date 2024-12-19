package com.czzy.core.mapper;

import com.czzy.core.entities.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-11-15
 */
@Mapper
public interface ClassMapper extends BaseMapper<Class> {

    /**
     * 增加自习室的人数
     */
    @Update("update tb_class set checked_num = checked_num + 1 where id = #{id}")
    int incrementCheckNum(String id);

    /**
     * 减少自习室的人数
     */
    @Update("update tb_class set checked_num = checked_num - 1 where id = #{id} and checked_num >= 0")
    int decrementCheckNum(String id);
}
