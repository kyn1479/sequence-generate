package com.kyn.sequencegeneratebydb.exception;


import com.kyn.sequencegeneratebydb.enums.ExceptionTypeEnum;

/**
 * @author yanan.kang
 * @description ：ErrorCode
 * @date 2020-03-28 9:55
 */
public interface ErrorCode {

    /**
     * 获取异常类型
     * @return
     */
    ExceptionTypeEnum getExceptionType();

    /**
     * 获取错误编码
     * @return
     */
    String getCode();

    /**
     * 获取错误消息
     * @return
     */
    String getMessage();

}
