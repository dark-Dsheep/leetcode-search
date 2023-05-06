package com.fqh.backend.handler;

import com.fqh.backend.common.CommonResult;
import com.fqh.backend.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public CommonResult<?> handleBusinessException(HttpServletRequest request, BusinessException e) {
        log.error("【BusinessException】, requestURL => {}, errorMessage => {}", request.getRequestURL(), e.getMessage());
        String md = "\uD83E\uDD16\n" + "::: tip\n  " + e.getMessage() + "\n:::\n";
        return CommonResult.fail(e, md);
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<?> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        log.error("【RuntimeException】, requestURL => {}, errorMessage => {}", request.getRequestURL(), e.getMessage());
        return CommonResult.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("【MethodArgumentNotValidException】, errorMessage => {}", e.getMessage());
        return CommonResult.fail(e.getMessage());
    }
}
