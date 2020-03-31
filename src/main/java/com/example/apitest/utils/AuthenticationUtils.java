package com.example.apitest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 13:39:58
 **/
public class AuthenticationUtils {
    public static Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);
    //生成签名
    public static String getSignature(String userName, String password,String realm,String randomKey,String encryptType) {
        String signature = MD5Utils.encrypt(password, encryptType);
        signature = MD5Utils.encrypt(userName+signature, encryptType);
        signature = MD5Utils.encrypt(signature, encryptType);
        signature = MD5Utils.encrypt(userName+":"+realm+":"+signature, encryptType);
        signature = MD5Utils.encrypt(signature+":"+randomKey, encryptType);
        return signature;
    }



}
