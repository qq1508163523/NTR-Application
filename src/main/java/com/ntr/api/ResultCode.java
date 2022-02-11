package com.ntr.api;

import com.ntr.api.restrict.IError;

public enum ResultCode implements IError {
    SUCCESS(200,"SUCCESS"),
    FAILED(500,"FAILED"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403,"FORBIDDEN");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
