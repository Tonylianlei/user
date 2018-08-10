package com.example.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
     * 加密
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String content) throws Exception {
        SecretKeySpec skeySpec = getKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return  encrypted;
    }

    /**
     * 解密
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] content) throws Exception {
        SecretKeySpec skeySpec = getKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(content);
        String originalString = new String(original);
        return originalString;
    }

    private static SecretKeySpec getKey() throws Exception {
        byte[] arrBTmp = KEY.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**
     * 加密
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content){
        try {
            return base64Encode(encrypt(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr){
        try {
            return encryptStr.isEmpty() ? null : decrypt(base64Decode(encryptStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return base64Code.isEmpty() ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }
}
