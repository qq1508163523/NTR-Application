package com.ntr.exception;

import com.ntr.api.CommonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({CommonException.class})
    public CommonResult<Object> commonException(CommonException e){
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResult<Object> constraintException(ConstraintViolationException e){
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<Object> bindException(MethodArgumentNotValidException e){
       return CommonResult.failed(e.getMessage());
    }

}
