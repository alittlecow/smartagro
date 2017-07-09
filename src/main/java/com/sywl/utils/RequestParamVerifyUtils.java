package com.sywl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Zhanglj on 2017/7/9.
 */
public class RequestParamVerifyUtils {

    //参数为空返回true
    public static boolean isEmpty(String param){
        if(param==null || param.equals("")){
            return true;
        }
        return false;
    }

    //校验正则表达式
    public static boolean regexMatches(String regex, String param) {
        if (Pattern.matches(regex, param)) {
            return true;
        }
        return false;
    }

    //校验日期格式
    public static boolean isDateFormated (String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //校验日期时间格式
    public static boolean isDateTimeFormated (String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
