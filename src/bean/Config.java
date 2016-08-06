package bean;

public class Config {
	
	//路径初始最大最小经纬度值，初始值应该相反赋值
	public static double LONG_MAX_INIT = 73;
	public static double LONG_MIN_INIT = 135;
	public static double LAT_MAX_INIT = 3;
	public static double LAT_MIN_INIT = 53;
	
	//精确度，即去除几位经纬度、时间
	public static int INIT_ACCURACY = 100000;
	public static int LONG_ACCURACY = 100;
	public static int LAT_ACCRACY = 100;
	public static int TIME_ACCRACY = 1000;
	
	//相似精度，即相似范围
	public static int LONG_SIM_ACCURACY = 2;
	public static int LAT_SIM_ACCURACY = 10;
	public static int TIME_SIM_ACCRACY = 100;
}
