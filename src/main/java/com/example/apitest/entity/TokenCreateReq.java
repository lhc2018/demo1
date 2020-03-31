package com.example.apitest.entity;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 13:47:33
 **/
public class TokenCreateReq implements Serializable {

    //用户名
    @NotBlank
    private String userName;

    //客户端类型
    @NotBlank
    private String clientType;

    //
    private String ipAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientType() {
        return "winpc";
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

    @Override
    public String toString() {
        return "TokenCreateReq{" +
                "userName='" + userName + '\'' +
                ", clientType='" + clientType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
