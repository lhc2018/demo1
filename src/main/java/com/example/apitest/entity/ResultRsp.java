package com.example.apitest.entity;



/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-26 14:05:30
 **/
public class ResultRsp<T> {

    private int code;

    private String message;

    private T t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public ResultRsp(int code) {
        this.code = code;
    }

    public ResultRsp(int code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }

    public ResultRsp(int code,String message){
        this.code = code;
        this.message = message;
    }
    public static <T> ResultRsp<T> success(T t){
        return new ResultRsp(200,"成功",t);
    }

    public static <T> ResultRsp<T> error(){
        return new ResultRsp(500,"失败");
    }


}
