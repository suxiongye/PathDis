package bean;

public class Node {
	private int id;
	private double longitude;
	private double latitude;

	public Node(int id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String out = "id:"+id+"\tlongitude:"+longitude+"\t latitude:"+latitude+"\t ";
		return out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
