package com.example.apitest.entity;

public enum CodeEnum {

    FIRST_SUCCESS(401);

    private int code;

    CodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
