package webservices;

public class Users {
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	
	public Users(int id,String usn,String pass,String na,String em,int age)
	{
		this.id=id;
		this.username=usn;
		this.password=pass;
		this.name=na;
		this.email=em;
		this.age=age;
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
	public String getName() {
		return this.name;
	}
	public String getEmail() {
		return this.email;
	}
	public int getAge() {
		return this.age;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(this.id)
			.append(", username=").append(this.username).append(", password=")
			.append(this.password).append(",name=")
			.append(this.name).append(", email=")
			.append(this.email).append(", age=")
			.append(this.age).append("]");
		return builder.toString();
	}
}
