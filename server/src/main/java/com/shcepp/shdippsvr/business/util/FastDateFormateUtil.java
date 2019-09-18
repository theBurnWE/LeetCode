package com.shcepp.shdippsvr.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastDateFormateUtil {
    /**
     * 日期时间格式:yyyy-mm-dd HH:mm:ss.
     */
    public static String dateTimePattern3 = "yyyy-MM-dd HH:mm:ss";
	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String timePattern2 = "yyyyMMddHHmmss";
	public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    public static String dateTimePattern2 = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static String dateToString(Date time){
		return dateToString(time,DATE_FORMAT);
	} 
	public static String dateToString(Date time, String formatDate){
	    SimpleDateFormat formatter;
	    formatter = new SimpleDateFormat(formatDate/*"yyyy-MM-dd HH:mm:ss SSS"*/);
	    String ctime = formatter.format(time);

	    return ctime; 
	}
	 public static String formatDate(String formatPattern, Date date) {
	        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
	        String returnValue="";
	        if(date!=null){
	            returnValue = f.format(date);
	        }
	        return returnValue;
	    }
	 public static final Date parseTrxTime(String trxTime) {
	        try {
	            return org.apache.commons.lang.time.DateUtils.parseDate(trxTime,
	                                                                     new String[] {
	                                                                         timePattern2,
	                                                                         dateTimePattern,
	                                                                         dateTimePattern2,
	                                                                         dateTimePattern3 });
	        } catch (ParseException e) {
	            return null;
	        }
	    }
}
