package com.sxs.mapper;

import com.sxs.entity.Health;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Somnambulist
 * @date 2022/4/22 19:11
 */

@SpringBootTest
public class HealthMapperTests {

    @Autowired
    private HealthMapper healthMapper;

    @Test
    void testGetAll(){
        List<Health> health = healthMapper.selectList(null);
        System.out.println(health);
    }

}
