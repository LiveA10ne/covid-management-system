package com.sxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxs.entity.Health;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Somnambulist
 * @date 2022/4/22 18:14
 */

@Mapper
public interface HealthMapper extends BaseMapper<Health> {
}
