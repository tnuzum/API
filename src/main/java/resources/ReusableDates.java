package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReusableDates {
	
	public static String getCurrentDate() {
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
	public static String getCurrentDateMinusOneYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, -1);
        Date currentDateMinusOneYear = c.getTime();
		return dateFormat.format(currentDateMinusOneYear);
	}
	public static String getNextSaturday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.SATURDAY, 1);
        Date getNextSaturday = c.getTime();
		return dateFormat.format(getNextSaturday);
	}
	public static String getNextSunday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.SUNDAY, 1);
        Date getNextSunday = c.getTime();
		return dateFormat.format(getNextSunday);
	}

};
	
	