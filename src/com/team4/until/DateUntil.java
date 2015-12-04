package com.team4.until;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUntil {

	//将"yyyy-MM-dd"格式转化为时间戳
	public static long dateToLong(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		return d.getTime()/1000;
		
	}
	//将时间戳格式转化为"yyyy-MM-dd"
	public static String longToDate(long date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(date*1000);
		return d;
	}
	//返回系统时间的时间戳
	public static long getLocationTime() {
		Date date = new Date();
		return date.getTime();
	}
	//返回系统时间 “yyyy-MM-dd”
	public static String getLocationTime1(){
		Date date = new Date();
		DateFormat df1 = DateFormat.getDateInstance();
		return String.valueOf(df1.format(date));
	}
	/*
	 * 判断时间先后
	 * date1在date2之前 返回true
	 * 反之返回false
	 */
	public static boolean judgeDate(String date1, String date2)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		if (d1.getTime() <= d2.getTime()) {
			return true;
		} else {
			return false;
		}
	}
}
