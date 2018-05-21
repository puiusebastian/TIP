package webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;




public class DBManager {
	private static final String URL = "jdbc:mysql://localhost:3306/tanks_war";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final DBManager instance = new DBManager();
	private Connection conn;
	public static DBManager getInstance() {
		return instance;
	}
	private DBManager() {
		System.out.println("Loading driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connection  to DataBase succeed!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//return list of users
	public List<Users> getUsersList() {
		try (Statement st = conn.createStatement()) {
			List<Users>  usersList= new ArrayList<Users>();
			st.execute("select * from Users");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Users user = new Users(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("email"),rs.getInt("age"));
				usersList.add(user);
			}
			// st.close();
			return usersList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//return list of tanks
	public List<Tanks> getTanksList(){
		try (Statement st = conn.createStatement()) {
			List<Tanks>  tanksList= new ArrayList<Tanks>();
			st.execute("select * from tanks");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Tanks tank = new Tanks(rs.getInt("tank_id"),rs.getInt("speed"),rs.getInt("health"),rs.getInt("damage"),rs.getInt("tank_range"),rs.getString("tank_name"));
				tanksList.add(tank);
			}
			// st.close();
			return tanksList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//return list of user_tank
	public List<User_tank> getUTList(){
		try (Statement st = conn.createStatement()) {
			List<User_tank>  utList= new ArrayList<User_tank>();
			st.execute("select * from user_tank");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				User_tank ut = new User_tank(rs.getInt("user_id"),rs.getInt("tank_id"));
				utList.add(ut);
			}
			// st.close();
			return utList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//return list of tank_picked
	public List<TankPicked> getTPList(){
		try(Statement st=conn.createStatement()){
			List<TankPicked>  tpList= new ArrayList<TankPicked>();
			st.execute("select * from tank_picked");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				TankPicked t = new TankPicked(rs.getString("user"),rs.getInt("tank"));
				tpList.add(t);
			}
			return tpList;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//insert User
	public boolean insertU(JsonObject info) {
		try(Statement st=conn.createStatement()){
			st.execute("insert into users values(null,'"+info.getString("username")+"','"+info.getString("password")+"','"+info.getString("name")+"','"+info.getString("email")+"',"+info.getInt("age")+")");
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//insert in tank_picked
		public boolean insertTP(JsonObject info) {
			try(Statement st=conn.createStatement()){
				st.execute("insert into tank_picked values('"+info.getString("user")+"',"+info.getInt("tank")+")");
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
	//delete user
	public boolean deleteU(int id) {
		try(Statement st=conn.createStatement()){
			st.execute("delete from users where id="+id);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//delete in tank_picked
	public boolean deleteTP(String user) {
		try(Statement st=conn.createStatement()){
			st.execute("delete from tank_picked where user='"+user+"'");
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//update user
	public boolean updateU(int id,JsonObject info) {
		try(Statement st=conn.createStatement()){
			st.execute("update users set username='"+info.getString("username")+"',password='"+info.getString("password")+"',name='"+info.getString("name")+"',email='"+info.getString("email")+"',age="+info.getInt("age")+" where id="+id);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}