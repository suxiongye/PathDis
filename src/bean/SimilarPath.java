package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//具有相似度的路径部分
public class SimilarPath {
	// 相似节点，用于前台输出
	private List<Node> nodes;
	private ArrayList<TimeNode> timeNodes;
	// 相似度列表
	private ArrayList<Map.Entry<String, Double>> simList;
	private Path mostPath;
	private TimePath mostTimePath;

	// 相似路径点个数
	private int simNum;
	// 最长相似路径长度
	private int maxLength;
	// 原路径节点总数
	private int nodes_all;

	public SimilarPath(List<Node> nodes, int simNum, int maxLength,
			int nodes_all) {
		this.nodes = nodes;
		this.simNum = simNum;
		this.maxLength = maxLength;
		this.nodes_all = nodes_all;
	}

	public SimilarPath(ArrayList<TimeNode> nodes_sim, int simNum,
			int maxLength, int nodes_all) {
		// TODO Auto-generated constructor stub
		this.simNum = simNum;
		this.maxLength = maxLength;
		this.nodes_all = nodes_all;
		this.setTimeNodes(nodes_sim);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public int getSimNum() {
		return simNum;
	}

	public void setSimNum(int simNum) {
		this.simNum = simNum;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getNodes_all() {
		return nodes_all;
	}

	public void setNodes_all(int nodes_all) {
		this.nodes_all = nodes_all;
	}

	/**
	 * 计算相似度
	 * 
	 * @return
	 */
	public double calculateSim() {
		// 计算节点数相似度
		double node_sim = (double) simNum / (double) nodes_all;
		node_sim = node_sim * Config.NODE_SIZE_SIM;
		// 计算连续长度相似度
		double node_length_sim = (double) maxLength / (double) nodes_all;
		node_length_sim = node_length_sim * Config.MAX_LENGTH_SIM;
		return new BigDecimal((node_sim + node_length_sim)*100).setScale(4,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public ArrayList<TimeNode> getTimeNodes() {
		return timeNodes;
	}

	public void setTimeNodes(ArrayList<TimeNode> timeNodes) {
		this.timeNodes = timeNodes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String out = "";
		if (nodes != null)
			for (Node node : nodes) {
				out += node.toString() + "\n";
			}
		if (timeNodes != null)
			for (TimeNode node : timeNodes) {
				out += node.toString() + "\n";
			}
		return out;
	}

	public Path getMostPath() {
		return mostPath;
	}

	public void setMostPath(Path mostPath) {
		this.mostPath = mostPath;
	}

	public TimePath getMostTimePath() {
		return mostTimePath;
	}

	public void setMostTimePath(TimePath mostTimePath) {
		this.mostTimePath = mostTimePath;
	}

	public ArrayList<Map.Entry<String, Double>> getSimList() {
		return simList;
	}

	public void setSimList(ArrayList<Map.Entry<String, Double>> simList) {
		this.simList = simList;
	}
}
