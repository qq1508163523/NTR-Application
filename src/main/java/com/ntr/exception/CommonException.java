package com.ntr.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException {

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
