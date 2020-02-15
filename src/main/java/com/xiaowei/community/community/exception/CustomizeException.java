package com.xiaowei.community.community.exception;

/**
 * Created by yxw on 2020/2/15
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public  CustomizeException(String message)
    {
        this.message = message;
    }

    public  CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
