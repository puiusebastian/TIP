package webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	private static final String URL = "jdbc:mysql://localhost:3306/my_database";
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
}