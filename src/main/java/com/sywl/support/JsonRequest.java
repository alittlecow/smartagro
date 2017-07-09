package com.sywl.support;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huzl
 * @version 1.0.0
 */
public class JsonRequest {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public String getOptString(String name) {
        return getString(name, null);
    }

    public String getString(String name) {
        String val = map.get(name);
        return val;
    }

    public Integer getInteger(String name) {
        String val = getString(name);
        return Integer.valueOf(val);
    }

    public String getString(String name, String defaultValue) {
        String val = map.get(name);
        return StringUtils.isEmpty(val) ? defaultValue : val;
    }

    public Integer getOptInteger(String name) {
        String val = map.get(name);
        return StringUtils.isEmpty(val) ? null : Integer.valueOf(val);
    }

    public Integer getInteger(String name, Integer defaultValue) {
        String val = map.get(name);
        return StringUtils.isEmpty(val) ? defaultValue : Integer.valueOf(val);
    }

    public Long getOptLong(String name) {
        String val = map.get(name);
        return StringUtils.isEmpty(val) ? null : Long.valueOf(val);
    }

    public Long getLong(String name) {
        String val = getString(name);
        return Long.valueOf(val);
    }

    public Long getLong(String name, Long defaultValue) {
        String val = getString(name);
        return StringUtils.isEmpty(val) ? defaultValue : Long.valueOf(val);
    }


    public Double getDouble(String name) {
        String val = getString(name);
        return new Double(val);
    }

    public Date getOptDate(String name, String datePattern) {
        String val = map.get(name);
        try {
            return StringUtils.isEmpty(val) ? null : new SimpleDateFormat(datePattern).parse(val);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public PageReq getPageReq() {
        return new PageReq(getInteger("pageNo", 0), getInteger("onePageNum", 10));
    }

    public TimeRange getTimeRange() {
        return getTimeRange(DEFAULT_DATE_PATTERN);
    }

    public TimeRange getTimeRange(String datePattern) {
        String strStartTime = getOptString("startTime");
        String strEndTime = getOptString("endTime");
        TimeRange timeRange = new TimeRange();
        try {
            if (StringUtils.isNotBlank(strStartTime)) {
                timeRange.setStartTime(DateUtils.parseDate(strStartTime, datePattern));
            }
            if (StringUtils.isNotBlank(strEndTime)) {
                timeRange.setEndTime(DateUtils.parseDate(strEndTime, datePattern));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return timeRange;
    }

    @JsonAnySetter
    public JsonRequest setProperty(String name, String value) {
        map.put(name, (String) value);
        return this;
    }

}
