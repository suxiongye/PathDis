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
	public static int LAT_ACCURACY = 100;
	public static int LONG_TIME_ACCURACY = 100;
	public static int LAT_TIME_ACCURACY = 100;
	public static int TIME_ACCRACY = 10000;
	
	//相似精度，即相似范围
	public static int LONG_SIM_ACCURACY = 40;
	public static int LAT_SIM_ACCURACY = 40;
	public static int LONG_TIME_SIM_ACCURACY = 30;
	public static int LAT_TIME_SIM_ACCURACY = 30;
	public static int TIME_SIM_ACCURACY = 1000;
	
	//相似度计算比例，最长相似路径和相似点总数比例
	public static double MAX_LENGTH_SIM = 0.5;
	public static double NODE_SIZE_SIM = 0.5;
	
	//数据库配置
	public static String DB_NAME = "PathDis";
	public static String TABLE_NAME = "path_history";
	public static String URL = "jdbc:mysql://127.0.0.1/";
	public static String DB_USER = "root";
	public static String DB_PASSWORD = "root";
	
}
