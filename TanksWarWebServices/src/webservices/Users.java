package webservices;

public class Users {
	private int id;
	private String username;
	private String password;
	
	public Users(int id,String usn,String pass)
	{
		this.id=id;
		this.username=usn;
		this.password=pass;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public int getId()
	{
		return this.id;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(this.id)
			.append(", username=").append(this.username).append(", password=")
			.append(this.password).append("]");
		return builder.toString();
	}
}
