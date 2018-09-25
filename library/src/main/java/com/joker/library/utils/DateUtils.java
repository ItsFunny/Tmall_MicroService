/**
*
* @author joker 
* @date 创建时间：2018年8月8日 上午9:15:39
* 
*/
package com.joker.library.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* 
* @author joker 
* @date 创建时间：2018年8月8日 上午9:15:39
*/
public class DateUtils
{
	public static void main(String[] args)
	{
		//获取当前的年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,0);
		c.add(Calendar.DAY_OF_WEEK, 1);
		System.out.println(c.getTime());
		System.out.println(c.getTimeInMillis());
		long rightNow = Long.parseLong(sdf.format(c.getTime()));
		System.out.println(rightNow);
	}
	
	public static Long getCurrentDay()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,0);
		long rightNow = Long.parseLong(sdf.format(c.getTime()));
		return rightNow;
	}
}	
