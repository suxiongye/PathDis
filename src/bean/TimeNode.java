package bean;

public class TimeNode extends Node {
	private long time;

	public TimeNode(int id, double longitude, double latitude, long time) {
		super(id, longitude, latitude);
		this.time = time;
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
}
