package com.fqh.backend.exception;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private final int errorCode;

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
