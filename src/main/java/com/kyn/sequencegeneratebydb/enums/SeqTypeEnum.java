package com.kyn.sequencegeneratebydb.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan.kang
 * @description ：类型枚举类
 * @date 2020-03-24 15:48
 */
public enum SeqTypeEnum {
    SEQ_DEPUTE("01", "代付", 1000),
    SEQ_DEDUCT("02", "代扣", 1000),
    SEQ_REFUNDED("03", "退款", 1000);
    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 枚举步长
     */
    private final int step;

    private SeqTypeEnum(String code, String message, int step) {
        this.code = code;
        this.message = message;
        this.step = step;
    }

    /**
     * 通过code获得枚举
     * @param code
     * @return
     */
    public static SeqTypeEnum getByCode(String code) {
        for (SeqTypeEnum _enum : values()) {
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
    public List<SeqTypeEnum> getAllEnum() {
        List<SeqTypeEnum> list = new ArrayList<SeqTypeEnum>();
        for (SeqTypeEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStep() {
        return step;
    }

    public String code() {
        return code;
    }
}
