package dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controller.PathController;
import util.Util;
import au.com.bytecode.opencsv.CSVReader;
import bean.Config;
import bean.Node;
import bean.Path;
import bean.TimeNode;
import bean.TimePath;

//该类负责路径的生成和初始化
public class PathDao {

	private static CSVReader csvReader;

	/**
	 * 读取文件名获取带时间的路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static Path createPath(String fileName) {
		// 设置最大最小经纬度
		double long_max = Config.LONG_MAX_INIT;
		double long_min = Config.LONG_MIN_INIT;
		double lat_max = Config.LAT_MAX_INIT;
		double lat_min = Config.LAT_MIN_INIT;

		ArrayList<Node> nodes = new ArrayList<Node>();
		int id = 1;
		Node node = null;
		double longitude;
		double latitude;

		try {
			InputStream in = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(in);
			csvReader = new CSVReader(inputStreamReader);
			String[] row = null;
			csvReader.readNext();

			while ((row = csvReader.readNext()) != null) {
				longitude = Double.parseDouble(row[0]);
				latitude = Double.parseDouble(row[1]);
				// 更新最大最小值
				if (longitude > long_max) {
					long_max = longitude;
				}
				if (longitude < long_min) {
					long_min = longitude;
				}
				if (latitude > lat_max) {
					lat_max = latitude;
				}
				if (latitude < lat_min) {
					lat_min = latitude;
				}
				node = new Node(id++, longitude, latitude);
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Path path = new Path(nodes);
		path.setMaxLongitude(long_max);
		path.setMaxLatitude(lat_max);
		path.setMinLongitude(long_min);
		path.setMinLatitude(lat_min);
		return path;

	}

	/**
	 * 读取文件名获取带时间的路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static TimePath createTimePath(String fileName) {
		// 设置最大最小经纬度
		double long_max = Config.LONG_MAX_INIT;
		double long_min = Config.LONG_MIN_INIT;
		double lat_max = Config.LAT_MAX_INIT;
		double lat_min = Config.LAT_MIN_INIT;
		ArrayList<TimeNode> nodes = new ArrayList<TimeNode>();
		int id = 1;

		TimeNode timeNode = null;
		double longitude;
		double latitude;

		try {
			InputStream in = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(in);
			csvReader = new CSVReader(inputStreamReader);
			String[] row = null;
			csvReader.readNext();

			while ((row = csvReader.readNext()) != null) {
				longitude = Double.parseDouble(row[0]);
				latitude = Double.parseDouble(row[1]);
				// 更新最大最小值
				if (longitude > long_max) {
					long_max = longitude;
				}
				if (longitude < long_min) {
					long_min = longitude;
				}
				if (latitude > lat_max) {
					lat_max = latitude;
				}
				if (latitude < lat_min) {
					lat_min = latitude;
				}
				timeNode = new TimeNode(id++, longitude, latitude,
						Util.timeToLong(row[2]));
				nodes.add(timeNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TimePath timePath = new TimePath(nodes);
		timePath.setMaxLongitude(long_max);
		timePath.setMaxLatitude(lat_max);
		timePath.setMinLongitude(long_min);
		timePath.setMinLatitude(lat_min);
		return timePath;
	}

	/**
	 * test function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Path path = PathDao
				.createPath("E:/中国软件杯/1_20160318100328_gjaaw/坐标点信息1(不含时间).csv");
		Path path2 = PathDao
				.createPath("E:/中国软件杯/1_20160318100328_gjaaw/坐标点信息2(不含时间).csv");
		// System.out.print(path);
		System.out.println("path length:" + path.getLength());
		System.out.println("path long_max:" + path.getMaxLongitude());
		System.out.println("path long_min:" + path.getMinLongitude());
		System.out.println("path lat_max:" + path.getMaxLatitude());
		System.out.println("path lat_min:" + path.getMinLatitude());

		TimePath timePath = PathDao
				.createTimePath("E:/中国软件杯/1_20160318100328_gjaaw/坐标点信息1(含时间).csv");
		TimePath timePath2 = PathDao
				.createTimePath("E:/中国软件杯/1_20160318100328_gjaaw/坐标点信息2(含时间).csv");
		// System.out.print(timePath);
		System.out.println("timePath length:" + timePath.getLength());
		System.out.println("timePath long_max:" + timePath.getMaxLongitude());
		System.out.println("timePath long_min:" + timePath.getMinLongitude());
		System.out.println("timePath lat_max:" + timePath.getMaxLatitude());
		System.out.println("timePath lat_min:" + timePath.getMinLatitude());

		PathController.comparePath(path, path2);
		PathController.compareTimePath(timePath, timePath2);
	}
}
