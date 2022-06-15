package com.sxs.controller;

import cn.hutool.core.util.StrUtil;
import com.sxs.common.Constants;
import com.sxs.common.Result;
import com.sxs.entity.User;
import com.sxs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Somnambulist
 * @date 2022/4/23 23:04
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public Result login(@RequestBody User user){
        User login = service.login(user);
        if (login == null){
            return Result.error(Constants.CODE_400,"参数错误");
        }else {
            return Result.success(login);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){

        if (service.register(user)){
            return Result.success(user);
        }else {
            return Result.error(Constants.CODE_400,"用户已存在");
        }
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        if (service.mySave(user)){
            return Result.success();
        }else {
            return Result.error(Constants.CODE_400,"保存错误");
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody User user){
        if (service.mySave(user)){
            return Result.success();
        }else {
            return Result.error(Constants.CODE_400,"修改错误");
        }
    }

}
