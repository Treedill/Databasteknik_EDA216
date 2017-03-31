package eda216_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public Set<String> getPalletID(){
		String sql = "SELECT palletID" + " FROM Pallets";
		return getSet(sql);
	}
	
	public Set<String> getCookieName(){
		String sql = "SELECT cookie" + " FROM Products";
		return getSet(sql);
	}
	
	public Optional<Integer> blockPallet(String palletID){
		return Optional.empty();
	}
	
	public Optional<Integer> producePallet(){
		return Optional.empty();
	}
	
	public String getPalletDateProduced(String cookie){
		String sql = "SELECT dateProduced " + " FROM Pallets " + "WHERE cookie=\"" + cookie + "\";";
		return getField(sql);
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
			// Enda sättet att ta ut nåt från ett set
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