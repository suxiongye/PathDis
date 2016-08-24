package util;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverSpi;

import au.com.bytecode.opencsv.CSVWriter;

public class PoiDataExcel {

	public static int NUM = 1000;
	
	public static void main(String[] args) {
		try {
			
			for(int i = 0; i< NUM;i++) {
				String[][] strings = PoiDataExcel.getRandData(48, true);
				PoiDataExcel.writeDataTOCsv(strings, true, i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String[][] getRandData(int count, boolean hasTime) {
		double lon = 118.00;
		double lat = 31.00;
		
		if(hasTime) {
			String[][] strArrs= new String[count][3];
			long nowMills = System.currentTimeMillis();
			long beforeMills = nowMills;
			for(int i = 0; i < count;i++) {
				
				int randomTime = (int)(1000000*Math.random());
				beforeMills+=randomTime;
				Timestamp now = new Timestamp(beforeMills);
				
				double rand1 = Math.random();
				double rand2 = Math.random();
				
				BigDecimal   b1   =   new   BigDecimal(rand1);  
				double   f1   =   b1.setScale(5,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				
				BigDecimal   b2   =   new   BigDecimal(rand2);  
				double   f2   =   b2.setScale(5,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				
				strArrs[i][0] = lon + f1 + "";
				strArrs[i][1] = lat + f2 + "";
				strArrs[i][2] = now.toLocaleString();
			}
			
			return strArrs;
		}
		else {
			String[][] strArrs= new String[count][2];
			for(int i = 0; i < count;i++) {
				
				strArrs[i][0] = (lon + Math.random()) + "";
				strArrs[i][1] = (lat + Math.random()) + "";
			}
			
			return strArrs;
		}
		
	}
	
	
	public static void writeDataTOCsv(String[][] strArrs, Boolean hasTime, int index) throws IOException {
		
		CSVWriter writer = new CSVWriter(new FileWriter("E:\\中国软件杯\\中国软件杯\\决赛文件\\测试数据\\分类测试数据\\带时间路径\\压力测试\\1000条数据\\"+index+".csv"));
		
		String[] title = {"经度","纬度", ""};
		
		if(hasTime) {
			title[2] = "发生时间";
		}
		
		writer.writeNext(title);
		for(int i = 0; i < strArrs.length; i++) {
			
			String[] strArr = strArrs[i]; 
				writer.writeNext(strArr);
		}
		
		writer.close();
	}
}
