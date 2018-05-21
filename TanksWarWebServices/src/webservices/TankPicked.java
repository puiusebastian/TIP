package webservices;

public class TankPicked {
	private String user;
	private int tank;
	
	public TankPicked(String u,int t) {
		this.user=u;
		this.tank=t;
	}
	
	public String getUserTP() {
		return this.user;
	}
	
	public int getTankTP() {
		return this.tank;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [user=").append(this.user).append(", tank=").append(this.tank).append("]");
		return builder.toString();
	}
}
