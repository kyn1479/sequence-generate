/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.kyn.sequencegeneratebydb.exception;


import java.text.MessageFormat;

/**
 * @author yanan.kang
 * @description ：业务异常
 * @date 2020-03-28 9:55
 */
public class PayException extends RuntimeException {

    /**
     * 错误码
     */
    private ErrorCode errorCode;

    /**
     * 异常参数，用于传参给最终的错误码
     */
    private Object[] args;

    public PayException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }

    public PayException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }


    public PayException(ErrorCode errorCode, Object... args) {
        super((errorCode.getCode()));
        this.errorCode = errorCode;
        this.args = args;
    }

    public PayException(ErrorCode errorCode, String args) {
        super((errorCode.getCode()));
        this.errorCode = errorCode;
        String [] temp = {args};
        this.args = temp;
    }


    public PayException(String message, ErrorCode errorCode, Object[] args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }


    public PayException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public PayException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }


    public PayException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    public String format(ErrorCode errorCode, Object[] args) {
        return MessageFormat.format(errorCode.getMessage(), args);
    }

    @Override
    public String getMessage() {
        return getErrorMsg();
    }

    public String getErrorMsg() {
        if (args != null) {
            return format(errorCode, args);
        }
        return errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
