package com.intest.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	@SuppressWarnings("static-access")
	public static int[] getTime() {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    
	    return new int[]{calendar.DAY_OF_MONTH,calendar.HOUR,calendar.MINUTE, calendar.SECOND};
	}
}
