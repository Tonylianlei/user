package com.example.demo.entity.dto;

import lombok.Data;

/**
 * 创建人:连磊
 * 日期: 2018/8/10. 16:25
 * 描述：
 */
@Data
public class UserLoginDto {

    //用户名
    private String userName;

    //密码
    private String password;

    //手机号
    private String phone;

}
