package com.sywl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * author：sunc
	 * version:1.0
	 *
	 * @param minutes
	 *
	 * @return
	 */
	public static Date getExpiryDate(int minutes) {

	// 根据当前日期，来得到到期日期
	Calendar calendar = Calendar.getInstance();

	calendar.setTime(new Date());
	calendar.add(Calendar.MINUTE, minutes);

	return calendar.getTime();
	}
	
	
	public static Date today() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			return df.parse(df.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date strToDate(String data) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			return df.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String todayStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
