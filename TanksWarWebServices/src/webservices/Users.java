package webservices;

public class Users {
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private int age;
	private int money;
	private int games_played;
	private int games_won;
	
	public Users(int id,String usn,String pass,String na,String em,int age,int money,int gp,int gw)
	{
		this.id=id;
		this.username=usn;
		this.password=pass;
		this.name=na;
		this.email=em;
		this.age=age;
		this.money=money;
		this.games_played=gp;
		this.games_won=gw;
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
	public int getMoney() {
		return this.money;
	}
	public int getGamesPlayed() {
		return this.games_played;
	}
	public int getGamesWon() {
		return this.games_won;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(this.id)
			.append(", username=").append(this.username).append(", password=")
			.append(this.password).append(",name=")
			.append(this.name).append(", email=")
			.append(this.email).append(", age=")
			.append(this.age).append(", money=").append(this.money).append(", games_played=").append(this.games_played).append(", games_won=").append(this.games_won).append("]");
		return builder.toString();
	}
}
