package webservices;

public class Users {
	private String username;
	private String password;
	
	public Users(String usn,String pass)
	{
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
	
}
