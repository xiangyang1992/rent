package com.keith.rent.core.exception;

import com.keith.rent.core.utils.HttpResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@RestControllerAdvice
public class ExceptionCtlAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult MethodArgNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        //提取错误信息返回
        return HttpResult.error(1001, "参数校验失败", error.getDefaultMessage());
    }

    @ExceptionHandler(RentException.class)
    public HttpResult RentExceptionHandler(RentException e) {
        return HttpResult.error(e.getCode(), "服务器出错!", e.getMsg());
    }
}
