package bean;

public class TimeNode extends Node {
	private long time;

	private String timeStr;
	
	public TimeNode(int id, double longitude, double latitude, long time, String timeStr) {
		super(id, longitude, latitude);
		this.time = time;
		this.setTimeStr(timeStr);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String out = super.toString()+"time:"+time;
		return out;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
}
