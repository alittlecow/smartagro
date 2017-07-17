package com.sywl.exception;

/**
 * @author Huzl
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {
    //异常消息
    private String errorMsg;


    public BusinessException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
