package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReusableDates {
	
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}
	
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}
	
	public static String getCurrentDatePlusOneDay() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);
        Date currentDatePlusOneDay = c.getTime();
		return dateFormat.format(currentDatePlusOneDay);
	}
	
	public static String getCurrentDatePlusOneDay8AM() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 08:00");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);
        Date currentDatePlusOneDay = c.getTime();
		return dateFormat.format(currentDatePlusOneDay);
	}
	
	public static String getCurrentDatePlusOneWeek() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 7);
        Date currentDatePlusOneWeek = c.getTime();
		return dateFormat.format(currentDatePlusOneWeek);
	}
	
	public static String getCurrentDatePlusOneMonth() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, 1);
        Date currentDatePlusOneMonth = c.getTime();
		return dateFormat.format(currentDatePlusOneMonth);
	}
	
	public static String getCurrentDatePlusOneYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, 1);
        Date currentDatePlusOneYear = c.getTime();
		return dateFormat.format(currentDatePlusOneYear);
	}
	
	public static String getCurrentDatePlusFiveYears() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, 5);
        Date currentDatePlusFiveYears = c.getTime();
		return dateFormat.format(currentDatePlusFiveYears);
	}
	
	public static String getCurrentDatePlusTenYears() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, 10);
        Date currentDatePlusTenYears = c.getTime();
		return dateFormat.format(currentDatePlusTenYears);
	}
	
	public static String getCurrentDateMinusOneYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, -1);
        Date currentDateMinusOneYear = c.getTime();
		return dateFormat.format(currentDateMinusOneYear);
	}
}
	
	