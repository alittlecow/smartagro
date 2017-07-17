package com.sywl.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author pengxiao
 * @date 2017/7/16
 */
public class SywlStringUtils {

    public static boolean checkAll(String... params) {
        for (String param : params) {
            if (StringUtils.isBlank(param))
                return false;
        }
        return true;
    }
}
