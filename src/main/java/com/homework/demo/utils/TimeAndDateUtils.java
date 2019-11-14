package com.homework.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeAndDateUtils {
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	static DateFormat timeFormat = new SimpleDateFormat("HHmm");
	static Date date = new Date();
	static String localDate = dateFormat.format(date);
	static String localTime = timeFormat.format(date);
	
	private TimeAndDateUtils() {
	}
	public static String getLocalDate() {
		return localDate;
	}
	public static String getLocalTime() {
		return localTime;
	}
	
	public static String getCSVFileName() {
		return "obsval"+ "_" +localDate + "_" + localTime;
	}
	
	public static String getErrorFileName() {
		return "ErrorFile"+ "_" +localDate + "_" + localTime;
	}
}
