package webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				Users user = new Users(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
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
				Tanks tank = new Tanks(rs.getInt("tank_id"),rs.getInt("speed"),rs.getInt("health"),rs.getInt("damage"),rs.getInt("missile_range"));
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
}