package bean;

import java.sql.Timestamp;

public class PathHistory {
	private int id;
	private String pathName;
	private Timestamp time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public PathHistory(int id, String pathName, Timestamp time) {
		this.id = id;
		this.pathName = pathName;
		this.time = time;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "history_id : " + this.getId() + " history_pathName: "
				+ this.getPathName() + " history_time: " + this.getTime();
	}
}
