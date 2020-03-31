package com.example.apitest.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 16:19:17
 **/
public class KeepTokenAliveReq implements Serializable {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
