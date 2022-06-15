package com.sxs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxs.entity.Health;
import com.sxs.service.impl.HealthServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Somnambulist
 * @date 2022/4/22 19:27
 */

@SpringBootTest
public class HealthServiceTests {

    @Autowired
    private IHealthService healthService = new HealthServiceImpl();

    @Test
    void testGetAll(){
        System.out.println(healthService.list(null));
    }

    @Test
    void tesrGetPage(){
        IPage<Health> page = new Page<>(0,1);
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        healthService.page(page,queryWrapper);
    }
}
