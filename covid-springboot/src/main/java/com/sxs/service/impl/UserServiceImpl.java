package com.sxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.entity.User;
import com.sxs.mapper.UserMapper;
import com.sxs.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Somnambulist
 * @date 2022/4/23 23:02
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

    @Override
    public User login(User user) {
        String no = user.getNo();
        String password = user.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no",no);
        queryWrapper.eq("password",password);
        User one = getOne(queryWrapper);
        return one;
    }

    @Override
    public boolean register(User user) {
        try {
            save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean mySave(User user) {
        String no = user.getNo();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no",no);
        return update(user,queryWrapper);
    }

}
