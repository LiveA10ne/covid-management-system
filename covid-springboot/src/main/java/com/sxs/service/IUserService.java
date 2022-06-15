package com.sxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxs.entity.User;

/**
 * @author Somnambulist
 * @date 2022/4/23 23:01
 */

public interface IUserService extends IService<User> {

    public User login(User user);

    public boolean register(User user);

    public boolean mySave(User user);
}
