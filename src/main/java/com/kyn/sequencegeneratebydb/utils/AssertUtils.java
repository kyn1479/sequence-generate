package com.kyn.sequencegeneratebydb.utils;

import com.kyn.sequencegeneratebydb.exception.ErrorCode;
import com.kyn.sequencegeneratebydb.exception.PayException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * @author yanan.kang
 * @description ：
 * @date 2020-03-28 10:12
 */
public class AssertUtils {

    /**
     * 判断是否空
     * @param object
     * @param errorCode
     * @throws PayException
     */
    public static void isNotNull(Object object, ErrorCode errorCode) throws PayException {
        if (object == null) {
            throw new PayException(errorCode);
        }
    }

    /**
     * 断言对象为空
     * @param object
     * @param errorCode
     * @param pattern
     * @param args
     * @throws PayException
     */
    public static void isNull(Object object, ErrorCode errorCode, String pattern, Object... args) throws PayException {
        if (object != null) {
            throw new PayException(errorCode, MessageFormat.format(pattern,args));
        }
    }

    /**
     * 断言对象非空
     * @param object
     * @param errorCode
     * @param message
     * @throws PayException
     */
    public static void isNotNull(Object object, ErrorCode errorCode, String message) throws PayException {
        if (object == null) {
            throw new PayException(errorCode, message);
        }
    }

    /**
     * 判断是否空
     * @param object
     * @param errorCode
     * @param args
     */
    public static void isNotNull(Object object, ErrorCode errorCode, Object[] args) {
        if (object == null) {
            throw new PayException(errorCode, args);
        }
    }

    /**
     * 判断是否空白
     * @param text
     * @param errorCode
     * @throws PayException
     */
    public static void isNotBlank(String text, ErrorCode errorCode) throws PayException {
        if (StringUtils.isBlank(text)) {
            throw new PayException(errorCode);
        }
    }

    /**
     * 断言字符串非空
     * @param text
     * @param errorCode
     * @param message
     * @throws PayException
     */
    public static void isNotBlank(String text, ErrorCode errorCode, String message) throws PayException {
        if (StringUtils.isBlank(text)) {
            throw new PayException(errorCode, message);
        }
    }

    /**
     * 断言字符串为空
     * @param text
     * @param errorCode
     * @param pattern
     * @param args
     * @throws PayException
     */
    public static void isBlank(String text, ErrorCode errorCode, String pattern, Object... args) throws PayException {
        if (StringUtils.isNotBlank(text)) {
            throw new PayException(errorCode, MessageFormat.format(pattern,args));
        }
    }

    /**
     * 断言对象非空
     * @param object
     * @param errorCode
     * @param pattern
     * @param args
     * @throws PayException
     */
    public static void isNotNull(Object object, ErrorCode errorCode, String pattern, Object... args) throws PayException {
        if (object == null) {
            throw new PayException(errorCode, MessageFormat.format(pattern,args));
        }
    }

    /**
     * 检测容器是否为空
     * @param collection
     * @param errorCode
     * @throws PayException
     */
    public static void isNotEmpty(Collection<?> collection, ErrorCode errorCode)
            throws PayException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new PayException(errorCode);
        }
    }

    /**
     * 检测容器是否为空
     * @param collection
     * @param errorCode
     * @param message
     * @throws PayException
     */
    public static void isNotEmpty(Collection<?> collection, ErrorCode errorCode,String message)
            throws PayException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new PayException(errorCode,message);
        }
    }
    /**
     * 判断真
     * @param expression
     * @param errorCode
     * @throws PayException
     */
    public static void isTrue(boolean expression, ErrorCode errorCode) throws PayException {
        if (!expression) {
            throw new PayException(errorCode);
        }
    }

    /**
     * 断言为真
     * @param expression
     * @param errorCode
     * @param message
     * @throws PayException
     */
    public static void isTrue(boolean expression, ErrorCode errorCode, String message) throws PayException {
        if (!expression) {
            throw new PayException(errorCode, message);
        }
    }

    /**
     * 断言表达式为真
     * @param expression
     * @param errorCode
     * @param pattern
     * @param args
     * @throws PayException
     */
    public static void isTrue(boolean expression, ErrorCode errorCode, String pattern, Object... args) throws PayException {
        if (!expression) {
            throw new PayException(errorCode, MessageFormat.format(pattern,args));
        }
    }

    /**
     * 判断假
     * @param expression
     * @param errorCode
     * @throws PayException
     */
    public static void isFalse(boolean expression, ErrorCode errorCode) throws PayException {
        if (expression) {
            throw new PayException(errorCode);
        }
    }

    /**
     * 断言为假
     * @param expression
     * @param errorCode
     * @param message
     * @throws PayException
     */
    public static void isFalse(boolean expression, ErrorCode errorCode, String message) throws PayException {
        if (expression) {
            throw new PayException(errorCode, message);
        }
    }

    /**
     * 断言表达式为假
     * @param expression
     * @param errorCode
     * @param pattern
     * @param args
     * @throws PayException
     */
    public static void isFalse(boolean expression, ErrorCode errorCode, String pattern, Object... args) throws PayException {
        if (expression) {
            throw new PayException(errorCode, MessageFormat.format(pattern,args));
        }
    }

}
