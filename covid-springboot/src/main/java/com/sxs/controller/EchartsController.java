package com.sxs.controller;

import com.sxs.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Somnambulist
 * @date 2022/4/24 17:36
 */

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    public Result get(){
        Map<String,Object> map = new HashMap<>();

        return Result.success();
    }

}
