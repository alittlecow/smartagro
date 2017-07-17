package com.sywl.utils;

import com.sywl.common.enums.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanglj on 2017/7/17.
 */
public class DateTimeUtils {
    /**
     * 获取当前时间字符串（格式为："yyyy-MM-dd HH:mm:ss"）
     *
     * @return String
     */
    public static String getCurrentTimeStr(){
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT_STR);//设置日期格式
        String currentTimeStr = df.format(new Date());// new Date()为获取当前系统时间
        return currentTimeStr;
    }

    /**
     * 获取当前日期字符串（格式为："yyyy-MM-dd"）
     *
     * @return String
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_STR);//设置日期格式
        String currentDateStr = df.format(new Date());// new Date()为获取当前系统时间
        return currentDateStr;
    }

    /**
     * 字符串转时间
     *
     * @param dateStr 格式为:“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static Date timeStrToDate(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT_STR);//设置日期格式
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转时间
     *
     * @param dateStr 格式为:“yyyy-MM-dd”
     * @return
     */
    public static Date dateStrToDate(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_STR);//设置日期格式
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据当前时间获得到期时间
     *
     * @param minutes 有效期
     * @return Date 到期时间
     */
    public static Date getExpiryDate(int minutes) {
        // 根据当前日期，来得到到期日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
