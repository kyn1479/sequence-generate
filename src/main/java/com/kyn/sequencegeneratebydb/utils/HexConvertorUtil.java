/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.kyn.sequencegeneratebydb.utils;

/**
 * @author yanan.kang
 * @description ：
 * @date 2020-03-28 10:18
 */
public class HexConvertorUtil {

    /**
     * 转换任意进制
     * @param num
     * @param hex
     * @return
     */
    public static String transform(long num, int hex) {
        //提高效率10进制直接返回；
        if (hex == 10){
            return String.valueOf(num);
        }
        long array[] = new long[100];
        int location = 0;
        while (num != 0) {
            long remainder = num % hex;
            num = num / hex;
            array[location] = remainder;
            location++;
        }
        return convert2String(array,location-1);
    }

    /**
     * 生成string
     * @param arr
     * @param hex
     * @return
     */
    private static String convert2String(long[] arr, int hex) {
        StringBuffer buffer = new StringBuffer();
        for (int i = hex; i >= 0; i--) {
            if (arr[i] > 9) {
                buffer.append((char) (arr[i] + 55));
            } else {
                buffer.append(arr[i] + "");
            }
        }
        return buffer.toString();
    }

}
