package com.fqh.backend.enums;

public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");

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