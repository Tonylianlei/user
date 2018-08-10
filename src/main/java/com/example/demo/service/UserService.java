package com.example.demo.service;

import com.example.demo.entity.dto.UserLoginDto;
import com.example.demo.entity.dto.UserRegisterDto;

/**
 * 创建人:连磊
 * 日期: 2018/8/10. 16:25
 * 描述：
 */
public interface UserService {

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:29
     *描    述：用户登录
     **/
    void userLogin(UserLoginDto userLoginDto);

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:49
     *描    述：用户注册
     **/
    void userRegister(UserRegisterDto userRegisterDto);

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:57
     *描    述：检验手机号是否存在
     **/
    void checkTel(String tel);

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:58
     *描    述：校验登录名是否存在
     **/
    void checkUserNmae(String loginName);

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:59
     *描    述：检验验证码（用于手机注册和登录）
     **/
    void checkVerificationcCode(String code);

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:03
     *描    述：检验身份证是否存在
     **/
    String checkIdentityEncrypt(String identityEncrypt);

}
