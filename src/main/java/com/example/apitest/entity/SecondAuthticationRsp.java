package com.example.apitest.entity;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 15:47:32
 **/
public class SecondAuthticationRsp implements Serializable {

    //有效时间
    private int duration;

    //令牌
    private String token;

    //用户名
    private String userName;

    //用户名对应的用户Id
    private String userId;

    //上次登录的Ip
    private String lastLoginIp;

    //版本
    private String version;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SecondAuthticationRsp{" +
                "duration=" + duration +
                ", token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
