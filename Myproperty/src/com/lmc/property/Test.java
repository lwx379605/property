package com.lmc.property;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		BigDecimal amountPaid = new BigDecimal("-12256.53225");
		System.out.println(amountPaid.intValue());
		System.out.println(amountPaid.floatValue());
		System.out.println(amountPaid.doubleValue());
		System.out.println(amountPaid.longValue());
		System.out.println(0L==0);
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(calendar.getTime());
		System.out.println("===========");
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.set(year, month, day);
		beginCalendar.set(Calendar.HOUR_OF_DAY, beginCalendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		beginCalendar.set(Calendar.MINUTE, beginCalendar.getActualMinimum(Calendar.MINUTE));
		beginCalendar.set(Calendar.SECOND, beginCalendar.getActualMinimum(Calendar.SECOND));
		Date beginDate = beginCalendar.getTime();
		System.out.println(beginDate.toString());
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(year, month, day);
		endCalendar.set(Calendar.HOUR_OF_DAY, beginCalendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		endCalendar.set(Calendar.MINUTE, beginCalendar.getActualMaximum(Calendar.MINUTE));
		endCalendar.set(Calendar.SECOND, beginCalendar.getActualMaximum(Calendar.SECOND));
		Date endDate = endCalendar.getTime();
		System.out.println(endDate.toString());
	}
}
