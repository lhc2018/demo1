package com.example.apitest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 15:10:35
 **/
public class MD5Utils {

    public static Logger log= LoggerFactory.getLogger(MD5Utils.class);

    /**
     * MD5加密字符串
     * @param   entryStr, encryptType
     */
    public static String encrypt(String entryStr,String encryptType) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = entryStr.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance(encryptType);
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("MD5生成失败", e);
            return null;
        }
    }
}
