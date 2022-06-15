package com.sxs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Somnambulist
 * @date 2022/4/22 18:07
 */

@Data
public class Health {
    private Integer id;
    private String no;
    private String name;
    private String college;
    private String major;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    private String location;
    private float temperature;
    private String cough;
    private String fever;
}
