package com.keith.rent.core.exception;

import lombok.Getter;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Getter
public class RentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public RentException() {
        this(1001, "接口错误");
    }

    public RentException(String msg) {
        this(1001, msg);
    }

    public RentException(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
