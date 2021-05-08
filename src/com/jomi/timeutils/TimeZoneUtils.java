package com.jomi.timeutils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class TimeZoneUtils {

	public static TimeZone _lzone=TimeZone.getDefault();
	public static final String ISOTIMEFORMAT="yyyy-MM-dd'T'HH:mm:ssz";
	
	public static boolean checkIfTimeZonesAreSame(TimeZone t1, TimeZone t2, TimeZone t3){
		
		long of1=t1.getRawOffset();
		long of2=t2.getRawOffset();
		long of3=t3.getRawOffset();
		return (of1 == of2 && of1 ==of3 && of2 ==of3);
	}
	
	public static Calendar normalizeTime(Calendar pref, TimeZone z){
        Calendar g=new GregorianCalendar();
        long l=pref.getTimeInMillis();
        if(l>0&&l<Long.MAX_VALUE){
            l-=z.getOffset(l);
            l+=_lzone.getOffset(l);
        }
        g.setTimeInMillis(l);
        return g;
    }
    public static Calendar customizeTime(Calendar pref, TimeZone z){
        Calendar g=new GregorianCalendar();
        long l=pref.getTimeInMillis();
        if(l>0&&l<Long.MAX_VALUE){
            l-=_lzone.getOffset(l);
            l+=z.getOffset(l);
        }
        g.setTimeInMillis(l);
        return g;
    }
    public static Calendar getUTCCalendar(){
        TimeZone t=TimeZone.getTimeZone("UTC");
        return new GregorianCalendar(t);
    }
    
    public static TimeZone getUTCTimeZone(){
    	return TimeZone.getTimeZone("UTC");
    }
    
    public static long getCurrentTime(){
    	return System.currentTimeMillis();
    }
    
    
	public static void main(String[] args) throws ParseException{
		/*Date d = new Date();
		System.out.println(d.getTime());
		GregorianCalendar c1 =new GregorianCalendar();
		System.out.println(c1.getTimeInMillis());
		GregorianCalendar c2 =new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		System.out.println(c2.getTimeInMillis());
		System.out.println(System.currentTimeMillis());*/
		//System.out.println(DateTimeZone.getAvailableIDs());
		/*String tzd = "America/Los_Angeles";
		//long t=getAbsoluteTime("2013-12-02T22:30:00-09:00",tzd);
		String t= formatAbsoluteTime(1418441909000L,tzd);
		System.out.println(t);
		t= formatAbsoluteTime(1418442529000L,tzd);
		System.out.println(t);
		t= formatAbsoluteTime(1418443109000L,tzd);
		System.out.println(t);
		t= formatAbsoluteTime(1418442449820L,tzd);
		System.out.println(t);
		t= formatAbsoluteTime(1418442510820L,tzd);
		System.out.println(t);
		//System.out.println(formatAbsoluteTime(t,tzd));
*/		
		long now = System.currentTimeMillis();
		System.out.println(formatAbsoluteTime(now, "Asia/Kolkata","EEE, dd MMM yyyy, KK:mm z"));
		
		
	}
	public static String getDeviceTimeZone(){
		return DateTimeZone.getDefault().getID();
	}
	public static String customFormat(long absoluteTime, String format, String timezone){
		DateTime dt =new DateTime(absoluteTime);
		dt = dt.withZone(DateTimeZone.forID(timezone));
		DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
		return fmt.print(dt);
	}
	
	public static long getAbsoluteTime(String dateTimeString, String tzd) throws ParseException{
		DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser()
									.withZone(DateTimeZone.forID(tzd));
		DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
				
		DateTime dt     = parser.parseDateTime(dateTimeString);
		//System.out.println(dt.getZone());
		
		System.out.println(formatter.print(dt)); 
		//DateTime dateTimeInLA = dt.withZone(DateTimeZone.forID("America/Los_Angeles"));
		//System.out.println(formatter.print(dateTimeInLA)); 
				
		return dt.getMillis();
	}
	
	
	
	public static String normalizedTimeZoneID(String tz){
		return DateTimeZone.forID(tz).getID();
	}
	
	public static String formatAbsoluteTime(long time, String tzd){
		DateTime dt =new DateTime(time);
		dt = dt.withZone(DateTimeZone.forID(tzd));
		DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
		return formatter.print(dt);
	}
	
	public static String formatAbsoluteTime(long time, String tzd, String pattern){
		DateTime dt =new DateTime(time);
		dt = dt.withZone(DateTimeZone.forID(tzd));
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		return formatter.print(dt);
	}
	
	public static String formatToFrienflyTime(long time, String tzd){
		DateTime dt =new DateTime(time);
		dt = dt.withZone(DateTimeZone.forID(tzd));
		DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy, KK:mm a zzz");
		return formatter.print(dt);
	}
	public static DateTime parseDateTime(String time, String format){
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		//DateTime date = formatter.withZone(DateTimeZone.forID("Europe/Paris")).parseDateTime(str);
		return formatter.parseDateTime(time);
	}
	
	public static String formatDateTime(DateTime time, String format){
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		//DateTime date = formatter.withZone(DateTimeZone.forID("Europe/Paris")).parseDateTime(str);
		return formatter.print(time);
	}
	
	
	
	public static long getCurrentAbsoluteTime(){
    	return System.currentTimeMillis();
    }
	
	public static String formatToFrienflyMinuteTime(long time, String tzd){
		DateTime dt =new DateTime(time);
		dt = dt.withZone(DateTimeZone.forID(tzd));
		DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, dd MMM yyyy, KK:mm zzz");
		return formatter.print(dt);
	}
	
}
