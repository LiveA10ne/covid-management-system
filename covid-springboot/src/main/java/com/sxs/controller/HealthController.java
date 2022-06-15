package com.sxs.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxs.entity.Health;
import com.sxs.service.IHealthService;
import com.sxs.service.impl.HealthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;


/**
 * @author Somnambulist
 * @date 2022/4/22 19:04
 */

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private IHealthService service = new HealthServiceImpl();

    @GetMapping("/page")
    public IPage<Health> getPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String no,
                                 @RequestParam(defaultValue = "") String name,
                                 @RequestParam(defaultValue = "") String college){
        IPage<Health> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        if (!"".equals(no)){
            queryWrapper.like("no",no);
        }
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if (!"".equals(college)){
            queryWrapper.like("college",college);
        }
        queryWrapper.orderByDesc("create_time");
        return service.page(page,queryWrapper);
    }

    @PostMapping
    public boolean save(@RequestBody Health health){
        return service.saveOrUpdate(health);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return service.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return service.removeByIds(ids);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<Health> list = service.list();

        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.addHeaderAlias("no","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("college","学院");
        writer.addHeaderAlias("major","专业");
        writer.addHeaderAlias("createTime","上传时间");
        writer.addHeaderAlias("location","位置");
        writer.addHeaderAlias("temperature","体温");
        writer.addHeaderAlias("cough","咳嗽");
        writer.addHeaderAlias("fever","发烧");

        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("打卡记录","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+ fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

}
