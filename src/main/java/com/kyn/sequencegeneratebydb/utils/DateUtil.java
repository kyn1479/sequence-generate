package com.kyn.sequencegeneratebydb.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yanan.kang
 * @description ：时间工具类
 * @date 2020-03-26 10:01
 */
public class DateUtil {
    private final static String pattern1 = "yyyyMMdd";
    private final static String pattern2 = "yyyy-MM-dd";
    private final static String pattern3 = "yyyy-MM-dd HH:mm:ss";
    private final static String pattern4 = "yyyyMMddHHmmss";

    /**
     * DateFormat的缓存容器 <br>
     * 减少初始化DateFormat的耗时，增加工具类性能
     */
    protected static final ConcurrentMap<String, DateFormat> FORMATER_CACHE = new ConcurrentHashMap<String, DateFormat>();

    /**
     * 年月日时分秒(无下划线) yyyyMMddHHmmss
     */
    public static final String DT_LONG = "yyyyMMddHHmmss";

    static ZoneOffset DEFAULT_OFFSET = null;

    static {
        Clock clock = Clock.systemDefaultZone();
        // called once
        final Instant now = clock.instant();
        DEFAULT_OFFSET = clock.getZone().getRules().getOffset(now);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static java.util.Date getCurrentDateTime() {
        return DateTime.now().toDate();
    }

    /**
     * 获取前minutes分的时间
     *
     * @param minutes
     * @return
     */
    public static Date getLastDateTimeByMinutes(int minutes) {
        return DateTime.now().minusMinutes(minutes).toDate();
    }

    /**
     * 获取minutes分之后的时间
     * @param minutes
     * @return
     */
    public static Date getNextDateTimeByMinutes(int minutes) {
        return DateTime.now().plusMinutes(minutes).toDate();
    }

    /**
     * @Description 返回yyyyMMddHHmmss格式
     * @Params
     * @Return
     * @Exceptions
     */
    public static String getTime14() {
        DateTime dt = new DateTime();
        return dt.toString("yyyyMMddHHmmss");
    }

    /**
     * @Description 返回yyyyMMddHHmmss格式
     * @Params
     * @Return
     * @Exceptions
     */
    public static String getTime14(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /**
     * @Description 获取格yyyy-MM-dd时间
     * @Params
     * @Return
     * @Exceptions
     */
    public static String getDate10() {
        DateTime dt = new DateTime();
        return dt.toString("yyyy-MM-dd");
    }


    /**
     * 获取格yyyyMMdd时间(当前时间)
     * @return
     */
    public static String getDate8() {
        DateTime dt = new DateTime();
        return dt.toString("yyyyMMdd");
    }

    /**
     * 获取格yyyyMMdd时间(传入时间)
     * @param date
     * @return
     */
    public static String getDate8(Date date) {
        DateTime dt = new DateTime(date.getTime());
        return dt.toString("yyyyMMdd");
    }

    /**
     * 获取时间戳 10位到秒
     *
     * @param date
     * @return
     */
    public static long getTimestamp10(LocalDateTime date) {
        return date.toEpochSecond(DEFAULT_OFFSET);
    }

    /**
     * 时间解析
     *
     * @param dateTime
     * @param pattren
     * @return
     */
    public static Date parseDateTime(String dateTime, String pattren) {
        return DateTimeFormat.forPattern(pattren).parseDateTime(dateTime).toDate();
    }

    /**
     * 日期/时间格式化
     *
     * @param date
     * @param pattren
     * @return
     */
    public static String format(java.util.Date date, String pattren) {
        return new DateTime(date).toString(pattren);
    }

    /**
     * 获取前days日的日期
     *
     * @param days
     * @return
     */
    public static java.sql.Date getLastDateByDays(int days) {
        return new java.sql.Date(DateTime.now().minusDays(days).withMillisOfDay(0).toDate().getTime());
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static final String dtSimpleFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(pattern2).format(date);
    }
    /**
     * 获取格式
     *
     * @param format
     * @return
     */
    public static final DateFormat getFormat(String format) {
        DateFormat dateFormat = FORMATER_CACHE.get(format);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(format);
            FORMATER_CACHE.put(format, dateFormat);
        }
        return dateFormat;
    }
    /**
     * 获取下一天 返回 dtSimple 格式字符
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getNextDay(Date date) throws ParseException {
        if (date != null) {
            Calendar cad = Calendar.getInstance();
            cad.setTime(date);
            cad.add(Calendar.DATE, 1);
            return DateUtil.dtSimpleFormat(cad.getTime());
        }
        return null;
    }

    /**
     * 将字符串按format格式转换为date类型
     *
     * @param str
     * @param format
     * @return
     */
    public static Date string2Date(String str, String format) {
        try {
            return getFormat(format).parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static final String simpleFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getFormat(pattern3).format(date);
    }
    /**
     * 返回长日期格式（yyyyMMddHHmmss格式）
     * @return
     */
    public static final String longDate(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(DT_LONG).format(date);
    }

    public static Date asDate(LocalDate localDate) {
        if(localDate == null){
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        if(localDateTime == null){
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        if(date == null){
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        if(date == null){
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
