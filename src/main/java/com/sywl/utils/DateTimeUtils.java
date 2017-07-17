package com.sywl.utils;

import com.sywl.common.enums.Constants;

import java.text.SimpleDateFormat;
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
}
