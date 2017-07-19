package com.sywl.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author pengxiao
 * @date 2017/7/16
 */
public class CommonUtils {


    /**
     * 校验string数组是否含有空字符串
     *
     * @param params
     * @return
     */
    public static boolean hasEmptyString(String... params) {
        for (String param : params) {
            if (StringUtils.isBlank(param))
                return false;
        }
        return true;
    }

    public static boolean isPhone(String phone) {
        // TODO: 2017/7/19
        return true;
    }
}
