package controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import bean.Config;
import bean.Node;
import bean.Path;
import bean.SimilarPath;

public class PathController {

	// 经纬度数组
	private static byte[] pathArray_longitude = null;
	private static byte[] pathArray_latitude = null;

	/**
	 * 比较源路径和目标路径
	 * 
	 * @param path_source
	 * @param path_dest
	 * @return
	 */
	public static SimilarPath comparePath(Path path_source, Path path_dest) {
		createPathArray(path_source);
		return null;
	}

	/**
	 * 生成路径数组
	 * 
	 * @param path
	 */
	private static void createPathArray(Path path) {
		int power_long = Config.INIT_ACCURACY / Config.LONG_ACCURACY;
		int long_max_int = (int) (path.getMaxLongitude() * power_long);
		int long_min_int = (int) (path.getMinLongitude() * power_long);
		int array_length_long = long_max_int - long_min_int + 2
				* Config.LONG_SIM_ACCURACY;
		// 增加相似范围
		pathArray_longitude = new byte[array_length_long];
		ArrayList<Node> nodes = (ArrayList<Node>) path.getNodes();
		// 循环初始化数组
		for (Node node : nodes) {
			int start_long = (int) (node.getLongitude() * power_long)
					- long_min_int;
			for (int i = start_long; i < start_long + Config.LONG_SIM_ACCURACY
					* 2; i++) {
				pathArray_longitude[i] = 1;
			}
		}

		System.out.println(array_length_long);
		for (int i = 0; i < 100; i++) {
			System.out.println(pathArray_longitude[i]);
		}
	}
}
