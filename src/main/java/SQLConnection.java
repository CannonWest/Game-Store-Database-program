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
	public static ArrayList<String> selectGames()	{
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query1 = "SELECT * FROM `Games` JOIN VideoGames USING(inventory_id) JOIN Products USING(inventory_id)";
			String query2 = "SELECT * FROM `Games` JOIN BoardGames USING(inventory_id) JOIN Products USING(inventory_id)";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query1);
			
			outp.add("VIDEO GAMES");
			while(rs.next()) {
				outp.add("ID: " + rs.getString("inventory_id") + ", Name: " + rs.getString("name") + ", Age Rating: " + rs.getString("age_rating") + ", Console: " + rs.getString("console")+", Publisher: " + rs.getString("publisher") + ", Price: " + rs.getString("price"));
			}
			
			rs = st.executeQuery(query2);
			outp.add("BOARD GAMES");
			while(rs.next()) {
				outp.add("ID: " + rs.getString("inventory_id") + ", Name: " + rs.getString("name") + ", Age Rating: " + rs.getString("age_rating") + ", Publisher: " + rs.getString("publisher") + ", Price: " + rs.getString("price"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outp;
	}
	public static ArrayList<String> selectOther()	{
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query = "SELECT * FROM `OtherItems` JOIN Products USING(inventory_id)";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			
			while(rs.next()) {
				outp.add("ID: " + rs.getString("inventory_id") + ", Name: " + rs.getString("name") + ", Price: " + rs.getString("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outp;
	}
	public static ArrayList<String> selectTransactions()	{
		ArrayList<String> outp = new ArrayList<String>();
		try {
			String query = "SELECT * FROM `Transactions`";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			
			while(rs.next()) {
				outp.add("ID: " + rs.getString("transaction_id") + ", Person: " + rs.getString("person_id") + ", Price: " + rs.getString("total_price"));
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
	public static void insertTransaction(ArrayList<String> inp)	{
		try {
			String query1 = "INSERT INTO Transactions (transaction_id, total_price, person_id) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(1) + "\",\"" + inp.get(2) + "\")";
			Statement st = connection.createStatement();
			st.executeUpdate(query1);
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
	public static void insertGame(ArrayList<String> inp, boolean video)	{
		try {
			String query1 = "INSERT INTO Games (Inventory_id, Age_rating, Genre, Publisher) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(3) + "\",\"" + inp.get(5) + "\",\"" + inp.get(4)+"\")";
			String query2;
			String query9 = null;
			if(video)	{
				query2 = "INSERT INTO VideoGames (Name, Inventory_id, console) VALUES (\"" + inp.get(1) + "\",\"" + inp.get(0) + "\",\"" + inp.get(6) + "\")";
				query9 = "INSERT IGNORE INTO Consoles (name) VALUES (\"" + inp.get(6) + "\")";
			}
			else
				query2 = "INSERT INTO BoardGames (Name, Inventory_id) VALUES (\"" + inp.get(1) + "\",\"" + inp.get(0) + "\")";
			String query3 = "INSERT INTO Products (Inventory_id, price) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(2) + "\")";
			String query5 = "INSERT IGNORE INTO Genres (name) VALUES (\"" + inp.get(5) + "\")";
			String query7 = "INSERT IGNORE INTO Publishers (name) VALUES (\"" + inp.get(4) + "\")";
			
			Statement st = connection.createStatement();
			st.executeUpdate(query3);
			st.executeUpdate(query5);
			st.executeUpdate(query7);
			if(video)	{
				st.executeUpdate(query9);
			}
			st.executeUpdate(query2);
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertOther(ArrayList<String> inp)	{
		try {
			String query1 = "INSERT INTO Products (Inventory_id, price) VALUES (\"" + inp.get(0) + "\",\"" + inp.get(2) + "\")";
			String query2 = "INSERT INTO OtherItems (name, Inventory_id) VALUES (\"" + inp.get(1) + "\",\"" + inp.get(0) + "\")";
			Statement st = connection.createStatement();
			st.executeUpdate(query1);
			st.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void deleteEmployee(String id)	{
		try {
			String query1 = "DELETE FROM `People` WHERE person_id = "+ id;
			Statement st = connection.createStatement();
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}