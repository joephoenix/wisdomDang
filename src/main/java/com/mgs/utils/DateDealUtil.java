package com.mgs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDealUtil {

	/**
	 * 比较两个时间的前后
	 * 
	 * @param DATE1
	 *            第一个时间
	 * @param DATE2
	 *            第二个时间
	 * @return 返回0表示相等；返回1表示DATE2在前；返回2表示DATE1在前。
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}
