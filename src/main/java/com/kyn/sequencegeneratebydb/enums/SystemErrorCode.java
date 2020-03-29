package com.kyn.sequencegeneratebydb.enums;


import com.kyn.sequencegeneratebydb.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan.kang
 * @description ：
 * @date 2020-03-26 21:42
 */
public enum SystemErrorCode  implements ErrorCode {
    BIZ_PARAM_NULL("G101", "业务参数为空异常"),
    SYSTEM_ERROR("G102", "系统内部异常"),
    SEQUENCE_GEN_ERR("G103", "流水号生成异常"),

    ;
    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造枚举对象
     * @param code
     * @param message
     */
    private SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    @Override
    public ExceptionTypeEnum getExceptionType() {
        return null;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return null;
    }

    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     * @param code
     * @return
     */
    public static SystemErrorCode getByCode(String code) {
        for (SystemErrorCode _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }
    /**
     * 获取全部枚举
     * @return
     */
    public static List<SystemErrorCode> getAllEnum() {
        List<SystemErrorCode> list = new ArrayList<SystemErrorCode>();
        for (SystemErrorCode _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return
     */
    public  static List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (SystemErrorCode _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

}
