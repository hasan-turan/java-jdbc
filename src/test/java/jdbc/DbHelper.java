package test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	public String userName = "root";
	public String password = "12345";
	public String dbUrl = "jdbc:mysql://localhost:3306/world?userSSL=false&useUniCode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, userName, password);
			System.out.println("Connection created!");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return connection;
	}

	public void showErrorMessage(SQLException exception) {
		System.out.println("Error: " + exception.getMessage());
		System.out.println("Error Code: " + exception.getErrorCode());
	}
}
