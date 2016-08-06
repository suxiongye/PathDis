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
			e.printStackTrace();
		}
		return date.getTime();
	}
	
	/**
	 * test function
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Util.timeToLong("2015/6/21  10:47:00"));
		System.out.println(Util.timeToLong("2015/6/21  12:59:00"));
	}
}
