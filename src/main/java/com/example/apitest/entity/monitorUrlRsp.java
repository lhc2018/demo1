package com.example.apitest.entity;

import java.io.Serializable;

/**
 * @program: demo
 * @description: 获取实时监控url
 * @author: liying
 * @create: 2020-03-26 17:15:48
 **/
public class monitorUrlRsp implements Serializable {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
