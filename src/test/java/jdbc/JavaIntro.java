package test.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaIntro {

	public static void main(String[] args) throws SQLException {
		// select();
		// insert();
		// update();
		delete();
	}

	public static void delete() throws SQLException {
		DbHelper dbHelper = new DbHelper();

		Connection connection = null;
		try {
			connection = dbHelper.getConnection();
			String query = "delete from  city  where ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, 4080);
			int rowCount = preparedStatement.executeUpdate();
			// or preparedStatement.execute();
			System.out.println(rowCount + " kayýt silindi!");
			preparedStatement.close();
		} catch (SQLException e) {
			dbHelper.showErrorMessage(e);
		} finally {
			connection.close();

			System.out.println("Connection closed!");
		}
	}

	public static void update() throws SQLException {
		DbHelper dbHelper = new DbHelper();

		Connection connection = null;
		try {
			connection = dbHelper.getConnection();
			String query = "update city set Name=?,CountryCode=?,District=?,Population=? where ID=4080";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "Düzce 1");
			preparedStatement.setString(2, "TUR");
			preparedStatement.setString(3, "Düzce");
			preparedStatement.setInt(4, 60000);
			int rowCount = preparedStatement.executeUpdate();
			// or preparedStatement.execute();
			System.out.println(rowCount + " kayýt güncellendi!");
			preparedStatement.close();
		} catch (SQLException e) {
			dbHelper.showErrorMessage(e);
		} finally {
			connection.close();

			System.out.println("Connection closed!");
		}
	}

	public static void insert() throws SQLException {
		DbHelper dbHelper = new DbHelper();

		Connection connection = null;
		try {
			connection = dbHelper.getConnection();
			String query = "insert into city(Name,CountryCode,District,Population) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "Düzce");
			preparedStatement.setString(2, "TUR");
			preparedStatement.setString(3, "Düzce");
			preparedStatement.setInt(4, 50000);
			int rowCount = preparedStatement.executeUpdate();
			// or preparedStatement.execute();
			System.out.println(rowCount + " kayýt eklendi!");
			preparedStatement.close();
		} catch (SQLException e) {
			dbHelper.showErrorMessage(e);
		} finally {
			connection.close();

			System.out.println("Connection closed!");
		}
	}

	public static void select() throws SQLException {
		DbHelper dbHelper = new DbHelper();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = dbHelper.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from country");
			ArrayList<Country> countries = new ArrayList<Country>();
			while (resultSet.next()) {
				countries.add(new Country(resultSet.getString("Code"), resultSet.getString("Name"),
						resultSet.getString("Continent"), resultSet.getString("Region")));
			}
			System.out.println(countries.size());
		} catch (SQLException e) {
			dbHelper.showErrorMessage(e);
		} finally {
			statement.close();
			connection.close();
			System.out.println("Connection closed!");
		}
	}
}
