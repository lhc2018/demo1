package com.example.apitest.entity;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 14:37:17
 **/
public class TokenCreateRsp {

    //域信息
    private String realm;
    //随机秘钥种子
    private String randomKey;
    //MD5算法
    private String encryptType;
    //方法
    private String method;

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "TokenCreateRsp{" +
                "realm='" + realm + '\'' +
                ", randomKey='" + randomKey + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
