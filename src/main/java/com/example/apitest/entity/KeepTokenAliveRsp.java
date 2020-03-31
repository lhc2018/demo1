package com.example.apitest.entity;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 16:21:46
 **/
public class KeepTokenAliveRsp extends ResultRsp{


    public KeepTokenAliveRsp(int code) {
        super(code);
    }

    public KeepTokenAliveRsp(int code, String message, Object o) {
        super(code, message, o);
    }

    public KeepTokenAliveRsp(int code, String message) {
        super(code, message);
    }
}
