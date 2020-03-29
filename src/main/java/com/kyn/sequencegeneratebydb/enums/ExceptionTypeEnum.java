package com.kyn.sequencegeneratebydb.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan.kang
 * @description ：异常枚举
 * @date 2020-03-24 15:50
 */
public enum ExceptionTypeEnum {
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

    PARAM_CHECK("PARAM_CHECK", "参数验证"),

    MESSAGE_ASSEMBLE("MESSAGE_ASSEMBLE", "报文组装"),

    COMMUNICATION("COMMUNICATION", "通讯异常"),

    BIZ_CHECK("BIZ_CHECK", "业务校验异常")

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
    private ExceptionTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }
    public String message() {
        return message;
    }

    /**
     * 通过枚举获得枚举
     * @param code
     * @return
     */
    public static ExceptionTypeEnum getByCode(String code) {
        for (ExceptionTypeEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     *  获取全部枚举
     * @return
     */
    public List<ExceptionTypeEnum> getAllEnum() {
        List<ExceptionTypeEnum> list = new ArrayList<ExceptionTypeEnum>();
        for (ExceptionTypeEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (ExceptionTypeEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }
}
