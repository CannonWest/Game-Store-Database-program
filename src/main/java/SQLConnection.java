import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SQLConnection {

	private static Connection connection;

	public static void openConnection() {
		String user = "westcc2";
		String password = "V00834092";
		String database = "westcc2";

		String url = "jdbc:mysql://3.234.246.29:3306/" + database;

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection()	{
		return connection;
	}
	
	public static void selectConsoles(ArrayList<String> list) {
		try {
			String query = "SELECT name FROM Consoles";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			System.out.println("QUERY: " + query);
			System.out.println("=== RESULTS === ");

			while(rs.next()) {
				System.out.println("Name: " + rs.getString(1));
			}

			System.out.println("=== /RESULTS === ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}