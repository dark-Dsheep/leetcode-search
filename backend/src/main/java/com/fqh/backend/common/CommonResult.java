package com.fqh.backend.common;

import com.fqh.backend.common.exception.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fqh.backend.common.enums.ResultCode.*;

@Getter
@Setter
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> ok(T data) {
        return new CommonResult<T>(SUCCESS.getCode(), SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> fail() {
        return new CommonResult<T>(FAILED.getCode(), FAILED.getMessage());
    }

    public static <T> CommonResult<T> fail(BusinessException e) {
        return new CommonResult<>(e.getErrorCode(), e.getMessage());
    }

    public static <T> CommonResult<T> fail(BusinessException e, T data) {
        return new CommonResult<>(e.getErrorCode(), e.getMessage(), data);
    }

    public static <T> CommonResult<T> fail(String errorMessage) {
        return new CommonResult<>(ARGS_NOT_VALID.getCode(), ARGS_NOT_VALID.getMessage());
    }

}
