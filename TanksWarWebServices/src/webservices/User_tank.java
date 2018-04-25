package webservices;

public class User_tank {
	private int user_id;
	private int tank_id;
	public User_tank(int id1,int id2)
	{
		this.user_id=id1;
		this.tank_id=id2;
		
	}
	public int getUT_userId()
	{
		return this.user_id;
	}
	public int getUT_tankId()
	{
		return this.tank_id;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User_tank [user_id=").append(this.user_id)
			.append(", tank_id=").append(this.tank_id).append("]");
		return builder.toString();
	}

}
