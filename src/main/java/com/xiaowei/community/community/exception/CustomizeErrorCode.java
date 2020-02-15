package com.xiaowei.community.community.exception;

/**
 * Created by yxw on 2020/2/15
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    public String message;

    CustomizeErrorCode(String message)
    {
        this.message = message;
    }
}
