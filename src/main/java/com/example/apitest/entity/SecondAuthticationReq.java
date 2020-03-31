package com.example.apitest.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: demo
 * @description: 二次认证入参
 * @author: liying
 * @create: 2020-03-26 15:21:31
 **/
public class SecondAuthticationReq implements Serializable {

    //用户名
    @NotBlank
    private String userName;
    //用户密码
    @NotBlank
    private String password;
    //签名
    @NotBlank
    private String signature;
    //随机密钥
    @NotBlank
    private String randomKey;
    //加密类型
    @NotBlank
    private String encryptType;
    //客户端类型
    @NotBlank
    private String clientType;

    @NotBlank
    private String realm;

    private String ipAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getEncryptType() {
        return "winpc";
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    @Override
    public String toString() {
        return "SecondAuthticationReq{" +
                "userName='" + userName + '\'' +
                ", signature='" + signature + '\'' +
                ", randomKey='" + randomKey + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", clientType='" + clientType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", password='" + password + '\'' +
                ", realm='" + realm + '\'' +
                '}';
    }
}
