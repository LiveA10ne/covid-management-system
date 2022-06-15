package com.sxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxs.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Somnambulist
 * @date 2022/4/24 15:41
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
