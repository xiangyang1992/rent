package com.keith.rent.core.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 *
 * @author caohb
 *
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String DEF_DATE = "yyyy-MM-dd";
    public static String DEF_TIME = "HH:mm:ss";
    public static String CUR_DATE = "yyyyMMdd";
    public static String SHORT_DATE = "yyMMdd";
    public static String DEF_DATE_TIME = DEF_DATE + " " + DEF_TIME;

    // 使用ThreadLocal缓存线程创建的SimpleDateFormat日期格式器
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 获取该线程缓存的SimpleDateFormat日期格式器
     *
     * @return
     */
    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat sdf = threadLocal.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(DEF_DATE);
            threadLocal.set(sdf);
        }
        return sdf;
    }

    /**
     * 使用指定的格式来格式化日期对象
     *
     * @param date
     *            日期
     * @param pattern
     *            格式字符串
     * @return 格式化日期字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = getDateFormat();
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /**
     * 使用指定的格式来解析日期字符串
     *
     * @param dateStr
     *            日期字符串
     * @param pattern
     *            格式字符串
     * @return 日期对象
     */
    public static Date parse(String dateStr, String pattern) {
        //如果为null或空串,直接返回null
        if (StringUtil.isNull(dateStr)) {
            return null;
        }

        SimpleDateFormat sdf = getDateFormat();
        Date d = null;
        try {
            sdf.applyPattern(pattern);
            d = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("不能识别的日期:" + dateStr, e);
        }
        return d;
    }

    /**
     * 返回以格式yyyy-MM-dd表示的日期
     *
     * @return
     */
    public static Date parseDefDate(String date) {
        return parse(date, DEF_DATE);
    }

    /**
     * 返回以格式HH:mm:ss表示的时间
     *
     * @return
     */
    public static Date parseDefTime(String time) {
        return parse(time, DEF_TIME);
    }

    /**
     * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间
     *
     * @return
     */
    public static Date parseDefDateTime(String dateTime) {
        return parse(dateTime, DEF_DATE_TIME);
    }

    /**
     * 返回以格式yyyy-MM-dd表示的当前日期字符串
     *
     * @return
     */
    public static String getCurrentDefDate() {
        return format(new Date(), DEF_DATE);
    }

    /**
     * 返回以格式yyyyMMdd表示的当前日期字符串
     *
     * @return
     */
    public static String getCurrentCurDate() {
        return format(new Date(), CUR_DATE);
    }

    /**
     * 返回以格式HH:mm:ss表示的时间字符串
     *
     * @return
     */
    public static String getCurrentDefTime() {
        return format(new Date(), DEF_TIME);
    }

    /**
     * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间字符串
     *
     * @return
     */
    public static String getCurrentDefDateTime() {
        return format(new Date(), DEF_DATE_TIME);
    }

    /**
     * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间字符串
     *
     * @return
     */
    public static String getDefDateTime(Date date) {
        return format(date, DEF_DATE_TIME);
    }

    /**
     * 返回以格式yyMMdd表示的当前日期字符串
     *
     * @return
     */
    public static String getCurrentShorDate() {
        return format(new Date(), SHORT_DATE);
    }

    /**
     * 获取带格式的时间戳字符串
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        return getCurrentTimestamp("yyyy-MM-dd HH:mm:ss.S");
    }

    /**
     * 获取不带格式的时间戳字符串
     *
     * @return
     */
    public static String getUnCurrentTimestamp() {
        return getCurrentTimestamp("yyyyMMddHHmmssS");
    }

    /**
     * 返回指定格式的当前时间戳
     *
     * @param pattern
     *            格式字符串
     * @return
     */
    public static String getCurrentTimestamp(String pattern) {
        SimpleDateFormat sdf = getDateFormat();
        sdf.applyPattern(pattern);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }


    /**
     * 根据date取得当前date所在的季度
     *
     * @return
     */
    public static int getQuarterByDate(Date date) {
        int qoq = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                qoq = 1;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                qoq = 2;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                qoq = 3;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                qoq = 4;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return qoq;
    }

    /**
     * 根据日期取年份
     * @param date
     * @return
     */
    public static int getYearByDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }


    public static Date dateAdd(Date date,int dateCnt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH , dateCnt);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    public static Date qoqAdd(Date date,int qoqCnt){
        int dateCnt = qoqCnt * 3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH , dateCnt);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    public static int getMonthNum(Date date1,Date date2) {
        Calendar cal1=Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2=Calendar.getInstance();
        cal2.setTime(date2);
        return (cal2.get(1)-cal1.get(1))*12+(cal2.get(2)-cal1.get(2));
    }

    public static Date getDateByQoq(String qoqStr) throws ParseException{
        String [] qoqs = qoqStr.split("-");
        String month = qoqs[1].equals("1") ? "01" : qoqs[1].equals("2") ? "04" : qoqs[1].equals("3") ? "07" : "09" ;
        Date date = new SimpleDateFormat("yyyy/mm/dd").parse(qoqs[0]+"/"+month+"/01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }


    public static Boolean compareDate(String  date1, String  date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Date date_time1 = parse(date1, DEF_DATE);
        Date date_time2 = parse(date2, DEF_DATE);
        if (date_time1 != null && date_time2!=null) {
            return date_time1.after(date_time2);
        }
        return false;
    }
}
