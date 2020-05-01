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

	public static ArrayList<String> selectConsoles() {
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query = "SELECT name FROM Consoles";
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				outp.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outp;
	}

	public static ArrayList<String> selectEmployees()	{
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query = "SELECT * FROM `People` JOIN Employees USING(person_id)";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			
			while(rs.next()) {
				outp.add("ID: " + rs.getString("person_id") + ", Name: " + rs.getString("name") + ", Phone: " + rs.getString("phone") + ", Email: " + rs.getString("email") +", Salary: " + rs.getString("salary"));
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return outp;
	}
	
	public static ArrayList<String> selectCustomers()	{
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query = "SELECT * FROM `People` JOIN Customers USING(person_id)";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			
			while(rs.next()) {
				outp.add("ID: " + rs.getString("person_id") + ", Name: " + rs.getString("name") + ", Phone: " + rs.getString("phone") + ", Email: " + rs.getString("email"));
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return outp;
	}
	
	public static void insertEmployee(ArrayList<String> inp)	{
		try {
			String query1 = "INSERT INTO People (person_id, name, phone, email) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(1) + "\",\"" + inp.get(2) + "\",\"" + inp.get(3)+"\")";
			String query2 = "INSERT INTO Employees (person_id, salary) VALUES (\"" + inp.get(0) + "\",\""+ inp.get(4)+ "\")";
			Statement st = connection.createStatement();
			st.executeUpdate(query1);
			st.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertCustomer(ArrayList<String> inp)	{
		try {
			String query1 = "INSERT INTO People (person_id, name, phone, email) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(1) + "\",\"" + inp.get(2) + "\",\"" + inp.get(3)+"\")";
			String query2 = "INSERT INTO Customers (person_id) VALUES (\"" + inp.get(0) + "\")";
			Statement st = connection.createStatement();
			st.executeUpdate(query1);
			st.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}