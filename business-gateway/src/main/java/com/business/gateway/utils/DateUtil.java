package com.business.gateway.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DateUtils
 * @Description: 日期帮助类
 * @author wupeng
 * @date 2016年9月7日 下午1:03:32
 */
public class DateUtil {

	// 默认日期格式
	public static final String DEFAULT_DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd";

	private DateUtil() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * @Title: getIntervalDays
	 * @Description: (计算两个日期相差天数)
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * @Title: format
	 * @Description: (格式化字符串)
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String format(Date time, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(time);
	}

	/**
	 * @Title: format
	 * @Description: (按默认形式格式化字符串)
	 * @param time
	 * @return
	 */
	public static String format(Date time) {
		return format(time, DEFAULT_DATE_TIME_FORMATTER);
	}
	
	/**
	 * @Title: format
	 * @Description: (按默认形式格式化字符串)
	 * @param time
	 * @return
	 */
	public static String format(long time) {
		return format(new Date(time), DEFAULT_DATE_TIME_FORMATTER);
	}
	
	/**
	 * @Title: format
	 * @Description: (按默认形式格式化字符串)
	 * @param time
	 * @return
	 */
	public static String format(String time) {
		return format(formatToDate(time), DEFAULT_DATE_TIME_FORMATTER);
	}
	
	/**
	 * @Title: format
	 * @Description: (按默认形式格式化字符串)
	 * @param time
	 * @return
	 */
	public static Date formatToDate(String time) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
		try {
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss
     * @author wupeng
     * @return
     */
    public static String getCurrentTime(){
    	SimpleDateFormat f = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
    	Date d = new Date();
    	return f.format(d);
    }
    /**
     * 获取当前日期：yyyy-MM-dd
     * @author wupeng
     * @return
     */
    public static String getCurrentDate(){
    	SimpleDateFormat f = new SimpleDateFormat(DEFAULT_DATE_FORMATTER);
    	Date d = new Date();
    	return f.format(d);
    }
    
  /**
   * 获取当前时间多少天后的时间，填负数则是多少天之前的时间
   * @author wupeng
   * @param days
   * @return
   */
    public static String getLateTime(int days){
    	SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
    	Calendar cal=Calendar.getInstance();
    	//当前时间
    	Date date = new Date();
    	cal.setTime(date);
    	//后多少天
        cal.add(Calendar.DATE, days);
    	date = cal.getTime();
    	return df.format(date);
    }
    /**
     * 获取指定日期多少天之后的时间，填负数则是多少天之前的时间
     */
    @SuppressWarnings("deprecation")
	public static String getLateTime(String nowTime,int days){
    	SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
    	Calendar cal=Calendar.getInstance();
    	Date date = new Date();
    	try {
			date=df.parse(nowTime);
		} catch (ParseException e) {
			date=new Date(nowTime);
		}
    	cal.setTime(date);
    	//后多少天
        cal.add(Calendar.DATE, days);
    	date = cal.getTime();
    	return df.format(date);
    }
    
    /**
     * 获取当前时间几个月后的时间,填负数则是几个月之前
     * @author wupeng
     * @param month
     * @return
     */
    public static String getLateTimeByMonth(int month){
    	SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
    	Calendar cal=Calendar.getInstance();
    	//当前时间s
    	Date date = new Date();
    	cal.setTime(date);
    	//后几个月
        cal.add(Calendar.MONTH, month);
    	date = cal.getTime();
    	return df.format(date);
    }
    /**
     * 获取两个日期之间相差的天数
     * @author wupeng
     * @param smdate
     * @param bdate
     * @return
     */
    public static int daysBetween(String smdate,String bdate){
    	SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATE_FORMATTER);
    	int betweenDay=0;
    	try{
    		Calendar cal = Calendar.getInstance();    
            cal.setTime(sdf.parse(smdate));    
            long time1 = cal.getTimeInMillis();                 
            cal.setTime(sdf.parse(bdate));    
            long time2 = cal.getTimeInMillis();         
            long between_days=(time2-time1)/(1000*3600*24);  
            betweenDay= Integer.parseInt(String.valueOf(between_days));  
    	}catch(Exception e){;}
    	return betweenDay;
    } 
    
   	/** 
	 * 根据日期获得所在周的日期  ,
	 * @param mdate 当前时间
	 *  @param len 表示周一到周几
	 * @return 
	 */  
	@SuppressWarnings("deprecation")  
	public static List<String> dateToWeek(Date mdate , int len) {  
		SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATE_FORMATTER);
	    int b = mdate.getDay();  
	    Date fdate;  
	    List<String> list = new ArrayList<String>();  
	    Long fTime = mdate.getTime() - b * 24 * 3600000;  
	    for (int a = 1; a <= len; a++) {  
	        fdate = new Date();  
	        fdate.setTime(fTime + (a * 24 * 3600000));  
	        list.add(a-1, sdf.format(fdate));  
	    }  
	    return list;  
	}  
	
	
	//格式化时分秒
	//hourMinSecond:00:00:00
	public static Date formatZeroPoint(Date date ,String  hourMinSecond){
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMATTER);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTimeStr =  sdf.format(date)+" " +hourMinSecond;		
		try {
			return format.parse(startTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
