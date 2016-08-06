package bean;

import java.util.List;

public class TimePath {
	private List<TimeNode> timeNodes;
	// 路径长度
	private int length;
	// 最大最小经纬度及时间
	private double maxLongitude;
	private double minLongitude;
	private double maxLatitude;
	private double minLatitude;
	private long maxTime;
	private long minTime;

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

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public TimePath(List<TimeNode> timeNodes) {
		this.timeNodes = timeNodes;
		this.length = timeNodes.size();
	}

	public List<TimeNode> getTimeNodes() {
		return timeNodes;
	}

	public void setTimeNodes(List<TimeNode> timeNodes) {
		this.timeNodes = timeNodes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String out = "";
		for (TimeNode node : timeNodes) {
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
