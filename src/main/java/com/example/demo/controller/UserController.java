package com.example.demo.controller;

import com.example.demo.entity.dto.UserLoginDto;
import com.example.demo.entity.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 创建人:连磊
 * 日期: 2018/8/10. 16:22
 * 描述： 用户信息
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:18
     *方 法 名：userLogin
     *传入参数：[userLoginDto]
     *返 回 值：void
     *描    述：用户登录
     **/
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public void userLogin( @RequestBody  UserLoginDto userLoginDto){
        userService.userLogin(userLoginDto);
    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:20
     *方 法 名：userRegister
     *传入参数：[userRegisterDto]
     *返 回 值：void
     *描    述：用户注册
     **/
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public void userRegister( @RequestBody UserRegisterDto userRegisterDto){
        userService.userRegister(userRegisterDto);
    }

}
