package com.example.demo.service.impl;

import com.example.demo.bean.LoveUser;
import com.example.demo.bean.LoveUserExample;
import com.example.demo.dao.LoveUserMapper;
import com.example.demo.entity.dto.UserLoginDto;
import com.example.demo.entity.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import com.example.demo.utils.DesEncrypt;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创建人:连磊
 * 日期: 2018/8/10. 16:29
 * 描述：
 * @author Tony
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private LoveUserMapper loveUserMapper;

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:29
     *方 法 名：userLogin
     *传入参数：[userLoginDto]
     *返 回 值：void
     *描    述：用户登录
     **/
    @Override
    public void userLogin(UserLoginDto userLoginDto) {
        if (!StringUtils.isEmpty(userLoginDto.getPassword())){
            userLoginDto.setPassword(DesEncrypt.aesEncrypt(userLoginDto.getPassword()));
        }
        LoveUserExample loveUserExample = new LoveUserExample();
        LoveUserExample.Criteria criteria = loveUserExample.createCriteria();
        if (StringUtils.isEmpty(userLoginDto.getPhone())){
            criteria.andLoginNameEqualTo(userLoginDto.getUserName()).andPasswordEqualTo(userLoginDto.getPassword());
        }else {
            //暂时不做验证码
            criteria.andTelEqualTo(userLoginDto.getPhone());
        }
        List<LoveUser> loveUsers = loveUserMapper.selectByExample(loveUserExample);
        if (loveUsers.size() > 0){
            throw new RuntimeException("账号异常");
        }else if (loveUsers.size() == 0){
            throw new RuntimeException("当前用户不存在");
        }
    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 16:49
     *方 法 名：userRegister
     *传入参数：[userRegisterDto]
     *返 回 值：void
     *描    述：用户注册
     **/
    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        checkUserNmae(userRegisterDto.getLoginName());
        checkTel(userRegisterDto.getTel());
        //加密身份证
        String identityId = userRegisterDto.getIdentityId();
        String identityEncrypt = checkIdentityEncrypt(identityId);
        LoveUser loveUser = new LoveUser();
        BeanUtils.copyProperties(userRegisterDto , loveUser);
        loveUser.setIdentityId(identityId.substring(0 , 3) + "****" + identityId.substring(identityId.length()-3 , identityId.length()));
        loveUser.setPassword(DesEncrypt.aesEncrypt(userRegisterDto.getPassword()));
        loveUserMapper.insert(loveUser);
    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:00
     *方 法 名：checkTel
     *传入参数：[tel]
     *返 回 值：void
     *描    述：检验手机号是否存在
     **/
    @Override
    public void checkTel(String tel) {

    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:00
     *方 法 名：checkUserNmae
     *传入参数：[loginName]
     *返 回 值：void
     *描    述：校验登录名是否存在
     **/
    @Override
    public void checkUserNmae(String loginName) {

    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:00
     *方 法 名：checkVerificationcCode
     *传入参数：[code]
     *返 回 值：void
     *描    述：检验验证码（用于手机注册和登录）
     **/
    @Override
    public void checkVerificationcCode(String code) {

    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/10 17:04
     *方 法 名：checkIdentityEncrypt
     *传入参数：[identityEncrypt]
     *返 回 值：java.lang.String
     *描    述：检验身份证是否存在
     **/
    @Override
    public String checkIdentityEncrypt(String identityEncrypt) {
        identityEncrypt = DesEncrypt.aesEncrypt(identityEncrypt);
        //未校验
        return  identityEncrypt;
    }
}
