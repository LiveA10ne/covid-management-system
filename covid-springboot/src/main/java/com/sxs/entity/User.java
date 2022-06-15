package com.sxs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Somnambulist
 * @date 2022/4/23 22:59
 */

@Data
public class User {
    private String no;
    private String password;
    private String name;
}
