package com.zgss.grib.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class DateUtil {
    public static String getIsoDateStr(int offset) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE, offset);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setCalendar(new GregorianCalendar(
                new SimpleTimeZone(0, "GMT")));
        return format.format(instance.getTime());
    }
    public static String getIsoDateStr(Date date,int offset) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE, offset);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setCalendar(new GregorianCalendar(
                new SimpleTimeZone(0, "GMT")));
        return format.format(instance.getTime());
    }
    public static Date getIsoDate(int offset) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MINUTE, offset);
        return instance.getTime();
    }
    public static Date getDate(int offset) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, offset);
        return instance.getTime();
    }

    public static Date getDate(Date date,int offset) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE, offset);
        return instance.getTime();
    }
    /**
     * 格林威治时间（GMT） 字符串转Date
     * 目前只有这种方法可行
     * @param strDate Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
     */
    public static Date parseGMT(String strDate) throws ParseException {
        if (strDate != null && strDate.trim().length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            format.setCalendar(new GregorianCalendar(
                    new SimpleTimeZone(0, "GMT")));
            Date date = format.parse(strDate);
            return date;
        }
        return null;
    }

    public static String getDateStr(Date date,int offset) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MINUTE, offset);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return format.format(instance.getTime());
    }

    public static void main(String[] args) {
//        System.out.println(getIsoDateStr(0));
//        System.out.println(getIsoDate(0));
//        System.out.println(getDate(4));
        try {
            Date date = parseGMT("2019-06-20T16:00:00.000Z");
            System.out.println(date.toLocaleString());
            System.out.println(getDate(date,12 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
