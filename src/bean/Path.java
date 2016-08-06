package bean;

import java.util.List;

public class Path {
	private List<Node> nodes;
	// 路径长度
	private int length;
	// 最大最小经纬度
	private double maxLongitude;
	private double minLongitude;
	private double maxLatitude;
	private double minLatitude;

	public double getMaxLongitude() {
		return maxLongitude;
	}

	public void setMaxLongitude(double maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	public double getMinLongitude() {
		return minLongitude;
	}

	public void setMinLongitude(double minLongitude) {
		this.minLongitude = minLongitude;
	}

	public double getMaxLatitude() {
		return maxLatitude;
	}

	public void setMaxLatitude(double maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	public double getMinLatitude() {
		return minLatitude;
	}

	public void setMinLatitude(double minLatitude) {
		this.minLatitude = minLatitude;
	}

	public Path(List<Node> nodes) {
		this.nodes = nodes;
		this.length = nodes.size();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String out = "";
		for (Node node : nodes) {
			out += node.toString() + "\n";
		}
		return out;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
