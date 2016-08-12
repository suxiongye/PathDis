package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bean.Config;
import bean.Node;
import bean.Path;
import bean.SimilarPath;
import bean.TimeNode;
import bean.TimePath;

//该类负责比较路径操作
public class PathController {

	// 经纬度数组
	private static boolean[][] pathArray = null;

	// 时间路径数组
	private static int[][][] timePathArray = null;

	/**
	 * 比较多路径，返回最相似路径
	 * 
	 * @param path
	 * @param paths
	 * @return
	 */
	public static SimilarPath comparePaths(Path path,
			HashMap<String, Path> paths) {
		initPathArray(path);
		// 相似度列表
		HashMap<String, Double> simMap = new HashMap<String, Double>();
		SimilarPath similarPath = null;
		// 最大相似路径
		SimilarPath mostPath = null;
		Double mostSim = 0.0;

		// 循环比较
		for (Entry<String, Path> p : paths.entrySet()) {
			similarPath = findSimPath(path, p.getValue());
			Double sim = similarPath.calculateSim();
			// 存储最大路径
			if (sim > mostSim) {
				mostSim = sim;
				mostPath = similarPath;
				mostPath.setMostPath(p.getValue());
			}

			simMap.put(p.getKey(), sim);
		}
		// 排序
		Comparator<Entry<String, Double>> comparator = new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> o1,
					Entry<String, Double> o2) {
				// TODO Auto-generated method stub
				return Double.compare(o2.getValue(), o1.getValue());
			}
		};
		ArrayList<Map.Entry<String, Double>> simList = new ArrayList<Map.Entry<String, Double>>(
				simMap.entrySet());
		Collections.sort(simList, comparator);
		mostPath.setSimList(simList);

		return mostPath;
	}

	/**
	 * 比较多条时间路径
	 * 
	 * @param timePath
	 * @param timePaths
	 * @return
	 */
	public static SimilarPath compareTimePaths(TimePath timePath,
			HashMap<String, TimePath> timePaths) {
		initTimePathArray(timePath);
		// 相似度列表
		HashMap<String, Double> simMap = new HashMap<String, Double>();
		SimilarPath similarPath = null;
		// 最大相似路径
		SimilarPath mostPath = null;
		Double mostSim = 0.0;

		// 循环比较
		for (Entry<String, TimePath> p : timePaths.entrySet()) {
			similarPath = findSimTimePath(timePath, p.getValue());
			Double sim = similarPath.calculateSim();
			// 存储最大路径
			if (sim > mostSim) {
				mostSim = sim;
				mostPath = similarPath;
				mostPath.setMostTimePath(p.getValue());
			}
			simMap.put(p.getKey(), sim);
		}
		// 排序
		Comparator<Entry<String, Double>> comparator = new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> o1,
					Entry<String, Double> o2) {
				// TODO Auto-generated method stub
				return Double.compare(o2.getValue(), o1.getValue());
			}
		};
		ArrayList<Map.Entry<String, Double>> simList = new ArrayList<Map.Entry<String, Double>>(
				simMap.entrySet());
		Collections.sort(simList, comparator);
		mostPath.setSimList(simList);

		return mostPath;

	}

	/**
	 * 比较源路径和目标路径
	 * 
	 * @param path_source
	 * @param path_dest
	 * @return
	 */
	public static SimilarPath comparePath(Path path_source, Path path_dest) {
		// 初始化数组路径
		initPathArray(path_source);
		return findSimPath(path_source, path_dest);
	}

	/**
	 * 比较带时间源路径和目标路径
	 * 
	 * @param path_source
	 * @param path_dest
	 * @return
	 */
	public static SimilarPath compareTimePath(TimePath path_source,
			TimePath path_dest) {
		// 初始化数组路径
		initTimePathArray(path_source);
		return findSimTimePath(path_source, path_dest);
	}

	/**
	 * 寻找当前路径与目标路径的相似部分
	 * 
	 * @param path_source
	 *            path_dest
	 * @return
	 */
	private static SimilarPath findSimPath(Path path_source, Path path_dest) {
		// 遍历节点，并将符合条件的放入新数组
		ArrayList<Node> nodes = (ArrayList<Node>) path_dest.getNodes();
		ArrayList<Node> nodes_sim = new ArrayList<Node>();

		int power_long = Config.INIT_ACCURACY / Config.LONG_ACCURACY;
		int power_lat = Config.INIT_ACCURACY / Config.LAT_ACCURACY;
		int long_min_int = (int) (path_source.getMinLongitude() * power_long);
		int lat_min_int = (int) (path_source.getMinLatitude() * power_lat);

		// 记录最长连续点
		int temp_length = 0;

		int max_length = 0;

		for (Node node : nodes) {
			// 计算对应经纬度
			int dest_long = (int) (node.getLongitude() * power_long)
					- long_min_int + Config.LONG_SIM_ACCURACY;
			int dest_lat = (int) (node.getLatitude() * power_lat) - lat_min_int
					+ Config.LAT_SIM_ACCURACY;
			// 若经纬度与目标路径相似则放入数组
			if (dest_long < pathArray.length && dest_lat < pathArray[0].length
					&& dest_long >= 0 && dest_lat >= 0
					&& pathArray[dest_long][dest_lat] == true) {
				nodes_sim.add(node);
				// 若是连续点，则加1
				if (temp_length != 0) {
					temp_length++;

				} else
					temp_length = 1;
				// 更新最大连续点
				if (temp_length > max_length)
					max_length = temp_length;
			} else {
				temp_length = 0;
			}
		}
		SimilarPath simPath = new SimilarPath(nodes_sim, nodes_sim.size(),
				max_length, nodes.size());
		System.out.println(nodes_sim.size() + " " + max_length);
		System.out.println(simPath.calculateSim());
		System.out.println(simPath);
		return simPath;
	}

	/**
	 * 寻找带时间当前路径与目标路径的相似部分
	 * 
	 * @param path_source
	 *            path_dest
	 * @return
	 */
	private static SimilarPath findSimTimePath(TimePath path_source,
			TimePath path_dest) {
		// 遍历节点，并将符合条件的放入新数组
		ArrayList<TimeNode> nodes = (ArrayList<TimeNode>) path_dest
				.getTimeNodes();
		ArrayList<TimeNode> nodes_sim = new ArrayList<TimeNode>();

		int power_long = Config.INIT_ACCURACY / Config.LONG_TIME_ACCURACY;
		int power_lat = Config.INIT_ACCURACY / Config.LAT_TIME_ACCURACY;
		int long_min_int = (int) (path_source.getMinLongitude() * power_long);
		int lat_min_int = (int) (path_source.getMinLatitude() * power_lat);

		// 记录最长连续点
		int temp_length = 1;
		int max_length = 0;

		TimeNode node = null;

		// 遍历路径，寻找相似点
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			// 计算对应经纬度
			int dest_long = (int) (node.getLongitude() * power_long)
					- long_min_int + Config.LONG_TIME_SIM_ACCURACY;
			int dest_lat = (int) (node.getLatitude() * power_lat) - lat_min_int
					+ Config.LAT_TIME_SIM_ACCURACY;
			// 计算时间差
			int time_dis = 0;
			int time_source = 0;
			if (i != 0) {
				time_dis = (int) (node.getTime() - nodes.get(i - 1).getTime())
						/ Config.TIME_ACCRACY;
			}

			// 若经纬度与目标路径相似则放入数组
			if (dest_long < timePathArray.length
					&& dest_lat < timePathArray[0].length && dest_long >= 0
					&& dest_lat >= 0
					&& timePathArray[dest_long][dest_lat][0] != -1) {
				time_source = getTimeDis(dest_long, dest_lat);

				// 若时间差在一定范围内则加入数组
				if (Math.abs(time_dis - time_source) <= Config.TIME_SIM_ACCURACY) {
					// 若前一个节点也在路径范围内则两个节点共同加入路径
					if (i != 0) {
						TimeNode front_node = nodes.get(i - 1);
						int front_long = (int) (front_node.getLongitude() * power_long)
								- long_min_int + Config.LONG_TIME_SIM_ACCURACY;
						int front_lat = (int) (front_node.getLatitude() * power_lat)
								- lat_min_int + Config.LAT_TIME_SIM_ACCURACY;
						if (front_long < timePathArray.length
								&& front_lat < timePathArray[0].length
								&& front_long >= 0
								&& front_lat >= 0
								&& timePathArray[front_long][front_lat][0] != -1) {
							// 判断是否存在相同节点
							boolean hasNode = false;
							for (Node f_node : nodes_sim) {
								if (f_node.getId() == front_node.getId()) {
									hasNode = true;
									break;
								}
							}
							if (!hasNode) {
								nodes_sim.add(front_node);
							}
							nodes_sim.add(node);
						}

					}
				}
			}
		}
		// 计算最长连续点
		for (int i = 0; i < nodes_sim.size(); i++) {
			if (i != 0) {
				if (nodes_sim.get(i - 1).getId() + 1 == nodes_sim.get(i)
						.getId()) {
					temp_length++;
					if (temp_length > max_length)
						max_length = temp_length;
				} else {
					temp_length = 1;
				}
			}
		}
		if (nodes_sim.size() == 0)
			temp_length = 0;
		SimilarPath simPath = new SimilarPath(nodes_sim, nodes_sim.size(),
				max_length, nodes.size());
		System.out.println(nodes_sim.size() + " " + max_length);
		System.out.println(simPath.calculateSim());
		System.out.println(simPath);
		return simPath;
	}

	/**
	 * 初始化路径数组
	 * 
	 * @param path
	 */
	private static void initPathArray(Path path) {
		int power_long = Config.INIT_ACCURACY / Config.LONG_ACCURACY;
		int power_lat = Config.INIT_ACCURACY / Config.LAT_ACCURACY;

		// 计算经纬度差值
		int long_max_int = (int) (path.getMaxLongitude() * power_long);
		int long_min_int = (int) (path.getMinLongitude() * power_long);
		int lat_max_int = (int) (path.getMaxLatitude() * power_lat);
		int lat_min_int = (int) (path.getMinLatitude() * power_lat);

		int array_length_long = long_max_int - long_min_int + 2
				* Config.LONG_SIM_ACCURACY + 1;
		int array_length_lat = lat_max_int - lat_min_int + 2
				* Config.LAT_SIM_ACCURACY + 1;

		// 增加相似范围
		pathArray = new boolean[array_length_long][array_length_lat];

		ArrayList<Node> nodes = (ArrayList<Node>) path.getNodes();
		// 循环初始化数组
		for (Node node : nodes) {
			int start_long = (int) (node.getLongitude() * power_long)
					- long_min_int;
			int start_lat = (int) (node.getLatitude() * power_lat)
					- lat_min_int;

			// 将具有经纬度的数组范围赋值为true
			for (int i = start_long; i <= start_long + Config.LONG_SIM_ACCURACY
					* 2; i++) {
				for (int j = start_lat; j <= start_lat
						+ Config.LAT_SIM_ACCURACY * 2; j++) {
					pathArray[i][j] = true;
				}
			}
		}
	}

	/**
	 * 初始化时间路径数组
	 * 
	 * @param path
	 */
	private static void initTimePathArray(TimePath path) {
		int power_long = Config.INIT_ACCURACY / Config.LONG_TIME_ACCURACY;
		int power_lat = Config.INIT_ACCURACY / Config.LAT_TIME_ACCURACY;

		// 计算经纬度差值
		int long_max_int = (int) (path.getMaxLongitude() * power_long);
		int long_min_int = (int) (path.getMinLongitude() * power_long);
		int lat_max_int = (int) (path.getMaxLatitude() * power_lat);
		int lat_min_int = (int) (path.getMinLatitude() * power_lat);

		int array_length_long = long_max_int - long_min_int + 2
				* Config.LONG_TIME_SIM_ACCURACY + 1;
		int array_length_lat = lat_max_int - lat_min_int + 2
				* Config.LAT_TIME_SIM_ACCURACY + 1;

		// 增加相似范围
		timePathArray = new int[array_length_long][array_length_lat][1];
		// 初始化
		for (int i = 0; i < timePathArray.length; i++) {
			for (int j = 0; j < timePathArray[0].length; j++) {
				timePathArray[i][j][0] = -1;
			}
		}

		ArrayList<TimeNode> nodes = (ArrayList<TimeNode>) path.getTimeNodes();

		// 循环初始化数组，设置路径范围数组
		for (TimeNode node : nodes) {
			int start_long = (int) (node.getLongitude() * power_long)
					- long_min_int;
			int start_lat = (int) (node.getLatitude() * power_lat)
					- lat_min_int;

			// 将具有经纬度的数组范围赋值为-2
			for (int j = start_long; j <= start_long
					+ Config.LONG_TIME_SIM_ACCURACY * 2; j++) {
				for (int k = start_lat; k <= start_lat
						+ Config.LAT_TIME_SIM_ACCURACY * 2; k++) {
					timePathArray[j][k][0] = -2;
				}
			}
		}
		TimeNode node = null;

		// 存储对应经纬度时间差
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			int time_dis = 0;
			// 计算每个节点和后一个节点的时间差
			if (i != 0) {
				time_dis = (int) (node.getTime() - nodes.get(i - 1).getTime())
						/ Config.TIME_ACCRACY;
			}
			int start_long = (int) (node.getLongitude() * power_long)
					- long_min_int;
			int start_lat = (int) (node.getLatitude() * power_lat)
					- lat_min_int;

			// 存储对应经纬度点的时间差
			timePathArray[start_long + Config.LONG_TIME_SIM_ACCURACY][start_lat
					+ Config.LAT_TIME_SIM_ACCURACY][0] = time_dis;
		}
	}

	/**
	 * 给定经纬度，寻找时间差
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	private static int getTimeDis(int longitude, int latitude) {
		// 寻找时间差
		int time_source = 0;
		int temp_long = -1;
		int temp_lat = -1;
		int temp_long2 = -1;
		int temp_lat2 = -1;

		// 遍历右上角方向
		outer: for (int k = longitude; k <= longitude
				+ Config.LONG_TIME_SIM_ACCURACY
				&& k < timePathArray.length; k++) {
			for (int j = latitude; j <= latitude + Config.LAT_TIME_SIM_ACCURACY
					&& j < timePathArray[0].length; j++) {
				if (timePathArray[k][j][0] >= 0) {
					temp_long = k;
					temp_lat = j;
					break outer;
				}
			}
		}

		// 遍历左下角方向
		outer: for (int k = longitude; k >= longitude
				- Config.LONG_TIME_SIM_ACCURACY
				&& k >= 0; k--) {
			for (int j = latitude; j >= latitude - Config.LAT_TIME_SIM_ACCURACY
					&& j >= 0; j--) {
				if (timePathArray[k][j][0] >= 0) {
					temp_long2 = k;
					temp_lat2 = j;
					break outer;
				}
			}
		}
		// 寻找距离较近的点
		if (temp_long >= 0 && temp_lat >= 0 && temp_long2 >= 0
				&& temp_lat2 >= 0) {
			if (temp_long - longitude + temp_lat - latitude > longitude
					- temp_long2 + latitude - temp_lat2) {
				time_source = timePathArray[temp_long2][temp_lat2][0];
			} else {
				time_source = timePathArray[temp_long][temp_lat][0];
			}
		} else if (temp_long >= 0 && temp_lat >= 0)
			time_source = timePathArray[temp_long][temp_lat][0];
		else if (temp_long2 >= 0 && temp_lat2 >= 0)
			time_source = timePathArray[temp_long2][temp_lat2][0];

		return time_source;
	}

}
