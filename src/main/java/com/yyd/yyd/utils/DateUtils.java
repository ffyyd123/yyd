package com.yyd.yyd.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtils {

    private static final DateTimeZone TIME_ZONE = DateTimeZone.forID("Asia/Shanghai");

    public static final String TIME_FORMAT_T1 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_T2 = "yyyy-MM-dd";
    public static final String TIME_FORMAT_T3 = "yyyyMMdd";
    public static final String TIME_FORMAT_T4 = "M月d日";
    public static final String TIME_FORMAT_T5 = "yyyy-MM";
    public static final String TIME_FORMAT_T6 = "yyyy";

    public static final DateTimeFormatter TIME_ZONE_FORMAT_T1 = DateTimeFormat.forPattern(TIME_FORMAT_T1).withZone(TIME_ZONE);
    public static final DateTimeFormatter TIME_ZONE_FORMAT_T3 = DateTimeFormat.forPattern(TIME_FORMAT_T3).withZone(TIME_ZONE);

    public static final SimpleDateFormat sdf_yyyy_MM_dd = new SimpleDateFormat(TIME_FORMAT_T2);
    public static final SimpleDateFormat sdf_yyyy_MM = new SimpleDateFormat(TIME_FORMAT_T5);
    public static final SimpleDateFormat sdf_yyyy = new SimpleDateFormat(TIME_FORMAT_T6);

    public static int currentTime() {
        return (int) (new DateTime().withZone(TIME_ZONE).getMillis() / 1000);
    }

    public static int today() {
        String newDay = new DateTime().withZone(TIME_ZONE).toString(TIME_FORMAT_T3);
        return Integer.parseInt(newDay);
    }

    public static String todayV1() {
        return new DateTime().withZone(TIME_ZONE).toString(TIME_FORMAT_T2);
    }

    /**
     * 获取当日最大秒数
     *
     * @return
     */
    public static int getTodayEndTime() {
        String fullDay = new DateTime().withZone(TIME_ZONE).toString(TIME_FORMAT_T2) + " 23:59:59";
        return (int) (DateTime.parse(fullDay, TIME_ZONE_FORMAT_T1).getMillis() / 1000);
    }

    /**
     * 获取当日最小秒数
     *
     * @return
     */
    public static int getTodayStartTime() {
        String fullDay = new DateTime().withZone(TIME_ZONE).toString(TIME_FORMAT_T2) + " 00:00:00";
        return (int) (DateTime.parse(fullDay, TIME_ZONE_FORMAT_T1).getMillis() / 1000);
    }

    /**
     * 时间戳转日期
     *
     * @param ts 时间戳 - 秒
     * @return
     */
    public static DateTime getDateTime(int ts) {
        return new DateTime(new Long((long) ts * 1000)).withZone(TIME_ZONE);
    }

    /**
     * @param ts             时间戳转日期
     * @param formatTemplate 格式化模板
     * @return
     */
    public static String getFormatTime(int ts, String formatTemplate) {
        return getDateTime(ts).toString(formatTemplate);
    }

    /**
     * 时间戳转日期
     *
     * @param ts 秒级
     * @return yyyyMMdd
     */
    public static String getFormatDate(int ts) {
        String formatDate = getDateTime(ts).toString(TIME_FORMAT_T1);
        return formatDate;
    }

    /**
     * 两个时间戳的天数差
     *
     * @param startTS 开始时间戳（秒）
     * @param endTS   结束时间戳（秒）
     * @return end - start
     */
    public static int daysBetween(int startTS, int endTS) {
        DateTime sdt = getDateTime(startTS).withTimeAtStartOfDay();
        DateTime edt = getDateTime(endTS).withTimeAtStartOfDay();

        return Days.daysBetween(sdt, edt).getDays();
    }

    /**
     * 计算应还款时间戳
     *
     * @param period   借款周期
     * @param loanTime 借款时间（到账时间）
     * @return
     */
    public static int getDueTime(int period, int loanTime) {
        String fullDay = getDateTime(loanTime).plusDays(period - 1).toString(TIME_FORMAT_T2) + " 23:59:59";
        return (int) (DateTime.parse(fullDay, TIME_ZONE_FORMAT_T1).getMillis() / 1000);
    }

    /**
     * 分期应还款时间
     *
     * @param month
     * @param loanTime
     * @return
     */
    public static int getDueMonthTime(int month, int loanTime) {
        String fullDay = getDateTime(loanTime).plusMonths(month).toString(TIME_FORMAT_T2) + " 23:59:59";
        return (int) (DateTime.parse(fullDay, TIME_ZONE_FORMAT_T1).getMillis() / 1000);
    }

    public static int nowMinusDays(int day) {
        String newDay = new DateTime().withZone(TIME_ZONE).minusDays(day).toString(TIME_FORMAT_T3);
        return Integer.parseInt(newDay);
    }

    public static String formatDate(int day, String formatTemplate) {
        return DateTime.parse(day + "", TIME_ZONE_FORMAT_T3).toString(formatTemplate);
    }


    public static int getTimeByStr(String timeStr) {
        int times = 0;
        try {
            Long timestamp = new SimpleDateFormat(TIME_FORMAT_T2).parse(timeStr).getTime();
            times = Integer.parseInt(String.valueOf(timestamp / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 日期获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取某年的开始时间 :某年的1月1号
     *
     * @return
     */
    public static Date getBeginDayOfYear(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(gc.get(1)));
        // cal.set
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取时间段内的每一天
     *
     * @param begintTime
     * @param endTime
     * @return
     */
    public static List<String> findDaysStr(String begintTime, String endTime) {
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = sdf_yyyy_MM_dd.parse(begintTime);
            dEnd = sdf_yyyy_MM_dd.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> daysStrList = new ArrayList<String>();
        daysStrList.add(sdf_yyyy_MM_dd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (dEnd.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String dayStr = sdf_yyyy_MM_dd.format(calBegin.getTime());
            daysStrList.add(dayStr);
        }
        return daysStrList;
    }


    /**
     * 获取时间段内的每月  yyyy-MM
     *
     * @param begintTime
     * @param endTime
     * @return
     */
    public static List<String> findMonthStr(String begintTime, String endTime) {
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = sdf_yyyy_MM_dd.parse(begintTime);
            dEnd = sdf_yyyy_MM_dd.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> monthsStrList = new ArrayList<String>();
        Date beginDayOfMonth = getBeginDayOfMonth(dBegin);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        Calendar calMonth = Calendar.getInstance();
        calMonth.setTime(beginDayOfMonth);
        while (!dEnd.before(calMonth.getTime())) {
            String monthStr = sdf_yyyy_MM.format(calMonth.getTime());
            monthsStrList.add(monthStr);
            calMonth.add(Calendar.MONTH, 1);
        }
        return monthsStrList;
    }


    public static List<String> findYearStr(String begintTime, String endTime) {
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = sdf_yyyy_MM_dd.parse(begintTime);
            dEnd = sdf_yyyy_MM_dd.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> yearsStrList = new ArrayList<String>();
        Date beginDayOfYear = getBeginDayOfYear(dBegin);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        Calendar calYear = Calendar.getInstance();
        calYear.setTime(beginDayOfYear);
        while (!dEnd.before(calYear.getTime())) {
            String yearStr = sdf_yyyy.format(calYear.getTime());
            yearsStrList.add(yearStr);
            calYear.add(Calendar.YEAR, 1);
        }
        return yearsStrList;
    }

    public static int strToTimestamp(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return (int) (sdf.parse(date).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
