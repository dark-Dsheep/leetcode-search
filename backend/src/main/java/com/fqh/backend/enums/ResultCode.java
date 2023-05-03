package com.fqh.backend.enums;

public enum ResultCode {

    SUCCESS(20000, "响应成功"),
    FAILED(50000, "系统错误"),
    NOT_FOUND(40001, "未找到该题目的题解");

    private final int code;
    private final String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}