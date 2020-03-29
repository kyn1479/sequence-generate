package com.kyn.sequencegeneratebydb.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanan.kang
 * @description ：进制转换枚举类
 * @date 2020-03-24 15:53
 */
public enum DecimalTypeEnum {
    DECIMAL("10", "十进制"),

    HEX_16("16", "十六进制"),

    HEX_32("32", "三十二进制"),

    HEX_36("36", "三十六进制")

    ;

    /** 枚举值 */
    private final String code;

    /** 枚举描述 */
    private final String message;


    /**
     * 构造函数
     * @param code
     * @param message
     */
    private DecimalTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code获得枚举
     * @param code
     * @return DecimalTypeEnum
     */
    public static DecimalTypeEnum getByCode(String code) {
        for (DecimalTypeEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     * @return List<DecimalTypeEnum>
     */
    public List<DecimalTypeEnum> getAllEnum() {
        List<DecimalTypeEnum> list = new ArrayList<DecimalTypeEnum>();
        for (DecimalTypeEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (DecimalTypeEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    private String code() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
