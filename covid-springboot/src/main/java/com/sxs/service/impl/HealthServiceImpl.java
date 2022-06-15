package com.sxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.entity.Health;
import com.sxs.mapper.HealthMapper;
import com.sxs.service.IHealthService;
import org.springframework.stereotype.Service;

/**
 * @author Somnambulist
 * @date 2022/4/22 19:02
 */

@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements IHealthService {
}
