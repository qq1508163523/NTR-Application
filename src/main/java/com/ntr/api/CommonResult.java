package com.ntr.api;


import lombok.Data;

@Data
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    protected CommonResult(){
    }

    private CommonResult(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data);
    }

    public static <T> CommonResult<T> success(T data, String message){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message ,data);
    }

    public static <T> CommonResult<T> failed(){
        return new CommonResult<T>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(),null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message,null);
    }

    public static <T> CommonResult<T> forbidden(){
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),null);
    }

    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), null);
    }

}
