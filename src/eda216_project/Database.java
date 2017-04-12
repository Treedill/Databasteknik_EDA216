package eda216_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class Database {

	/**
	 * The database connection.
	 */
	private Connection conn;

	/**
	 * Create the database interface object. Connection to the database is
	 * performed later.
	 */
	public Database() {
		conn = null;
	}

	/**
	 * Open a connection to the database, using the specified user name and
	 * password.
	 */
	public boolean openConnection(String filename) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if the connection to the database has been established
	 * 
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}

	public Set<String> getPalletID() {
		String sql = "SELECT palletID" + " FROM Pallets";
		return getSet(sql);
	}

	public String getPalletID(String cookie) {
		String sql = "SELECT palletID " + " FROM Pallets " + "WHERE cookie=\"" + cookie + "\";";
		return getField(sql);
	}

	public Set<String> getCookieDatePallets(String cookie, String date1, String date2) {
		String sql = "SELECT palletID " + "FROM Pallets " + "WHERE cookie=\"" + cookie + "\" AND dateProduced >=\""
				+ date1 + "\" AND dateProduced <=\"" + date2 + "\";";

		return getSet(sql);
	}

	public Set<String> getDatePallets(String date1, String date2) {
		String sql = "SELECT palletID " + "FROM Pallets " + "WHERE dateProduced >=\"" + date1
				+ "\" AND dateProduced <=\"" + date2 + "\";";
		return getSet(sql);
	}

	public Set<String> getCookiePallets(String cookie) {
		String sql = "SELECT palletID" + " FROM Pallets" + " WHERE cookie =\"" + cookie + "\";";
		return getSet(sql);
	}

	public Set<String> getBlockedPallets() {
		String sql = "SELECT palletID" + " FROM Pallets" + " WHERE isBlocked = 1";
		return getSet(sql);
	}

	public Set<String> getCustomerPallets(String customer) {
		String sql = "SELECT palletID"
				+ " FROM Pallets NATURAL JOIN OrderItems NATURAL JOIN Orders NATURAL JOIN Customers "
				+ " WHERE customerName =\"" + customer + "\";";
		return getSet(sql);
	}

	public String isBlocked(String palletID) {
		String sql = "SELECT isBlocked " + " FROM Pallets " + " WHERE palletID =\"" + palletID + "\";";
		if (getField(sql).contains("1")) {
			return "BLOCKED";
		} else {
			return "Not Blocked";
		}
	}

	public Set<String> getCookieName() {
		String sql = "SELECT cookie" + " FROM Products";
		return getSet(sql);
	}

	public String getCookie(String palletID) {
		String sql = "SELECT cookie" + " FROM Pallets" + " WHERE palletID =\"" + palletID + "\";";
		return getField(sql);
	}

	public Set<String> getCustomers() {
		String sql = "SELECT customerName" + " FROM Customers";
		return getSet(sql);
	}

	public String getCustomer(String palletID) {
		String sql = "SELECT customerName FROM Customers NATURAL JOIN Orders NATURAL JOIN Pallets"
				+ " WHERE palletID =\"" + palletID + "\";";
		return getField(sql);
	}

	public String getPalletDateProduced(String palletID) {
		String sql = "SELECT dateProduced " + " FROM Pallets " + " WHERE palletID=\"" + palletID + "\";";
		return getField(sql);
	}

	public Set<String> getCookieDateProduced(String cookie) {
		String sql = "SELECT dateProduced " + " FROM Pallets " + "WHERE cookie=\"" + cookie + "\";";
		return getSet(sql);
	}

	public String getIngredientAmount(String ingredient, String cookie) {
		String sql = "SELECT amount" + " FROM RecipeItems" + " WHERE ingredient =\"" + ingredient + "\" AND cookie =\""
				+ cookie + "\";";
		return getField(sql);
	}

	public Set<String> getIngredients(String cookie) {
		String sql = "SELECT ingredient" + " FROM RecipeItems" + " WHERE cookie =\"" + cookie + "\";";
		return getSet(sql);
	}
	
	public String getAmountInStorage(String ingredient){
		String sql = "SELECT amountStorage" + " FROM Ingredients" + " WHERE ingredient =\"" + ingredient + "\";";
		return getField(sql);
	}

	public void getAmountInStorage() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT ingredient, amountStorage FROM Ingredients");
			while (rs.next()) {
				String ing = rs.getString("ingredient");
				String amt = rs.getString("amountStorage");
				System.out.println("Ingredient: " + ing + "\n Amount: " + amt);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void blockPallet(String palletID) {
		String sql = "UPDATE Pallets SET isBlocked = 1 WHERE palletID =\"" + palletID + "\";";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// kod nåt sånt iaf
	public int producePallet(String cookie) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime time = LocalTime.now();
		String f = formatter.format(time);
		LocalDate date = LocalDate.now();
		try {
			conn.setAutoCommit(true);
			Statement s = conn.createStatement();
			String sql = "INSERT INTO Pallets(cookie, dateProduced, isBlocked) VALUES(\"" + cookie + "\",\"" + date
					+ " " + f + "\",\"" + 0 + "\");";
			String produce = "SELECT last_insert_rowid() AS pallID";
			s.executeUpdate(sql);
			ResultSet rs = s.executeQuery(produce);
			if (!rs.next()) {
				return 0;
			} else {
				return rs.getInt("pallID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void updateIngredients(String amount, String ingredient) {
		String sql = "UPDATE Ingredients SET amountStorage = amountStorage -" + amount + " WHERE ingredient =\""
				+ ingredient + "\";";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Set<String> getSet(String sql) {
		Set<String> found = new HashSet<>();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				found.add(rs.getString(1));
			}
			return found;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return found;
	}

	private String getField(String sql) {
		String field = null;
		Set<String> set = getSet(sql);
		if (set.size() == 1) {
			Iterator<String> itr = set.iterator();
			field = itr.next();
		}
		return field;
	}

	private int executeUpdate(String sql) {
		try {
			Statement s = conn.createStatement();
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return -1;
	}
}