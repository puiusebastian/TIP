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
	public List<Student> getStudentList() {
		try (Statement st = conn.createStatement()) {
			List<Student> studentList = new ArrayList<Student>();
			st.execute("select * from Student");
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Student student = new Student(rs.getInt("nr_matricol"),
					rs.getString("nume"), rs.getString("facultate"),
					rs.getInt("an"), rs.getInt("nota"));
				studentList.add(student);
			}
			// st.close();
			return studentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}