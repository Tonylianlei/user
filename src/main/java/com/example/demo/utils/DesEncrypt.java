package com.example.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 创建人:连磊
 * 日期: 2018/7/19. 19:27
 * 描述：
 */
public class DesEncrypt {

    /**
     * 加密时的key
     */
    public static String KEY = "@#!-*^";

    /**
     *开 发 者：连磊
     *开发时间：2018/8/14 14:27
     *方 法 名：encryption
     *传入参数：[data]
     *返 回 值：java.lang.String
     *描    述：加密
     **/
    public static String encryption(String data){
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *开 发 者：连磊
     *开发时间：2018/8/14 14:28
     *方 法 名：decrypt
     *传入参数：[parameter]
     *返 回 值：java.lang.String
     *描    述：解密
     **/
    public static String decrypt(String parameter){
        return new String(Base64.getDecoder().decode(parameter) , StandardCharsets.UTF_8);
    }
}
