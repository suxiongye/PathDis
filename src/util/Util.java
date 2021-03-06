package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	/**
	 * 将日期字符串转换为长整形
	 * @param time
	 * @return
	 */
	public static long timeToLong(String time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = simpleDateFormat.parse(time);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return date.getTime();
	}
	
	public static String timeToString(Long time){
		Date date = new Date(time);
		return date.toString();
	}
	/**
	 * test function
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Util.timeToLong("2016/8/24 1:38:29"));
		System.out.println(Util.timeToLong("2015/6/21  10:00:00"));
		System.out.println(Util.timeToString(Long.parseLong("1434862740000")));
	}
}
