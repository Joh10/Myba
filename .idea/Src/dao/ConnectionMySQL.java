package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
	private static String hostname = "pi_server_host";
	private static int port = 3306;
	private static String database = "pi";
	private static String username = "pi";
	private static String password = "azertyuiop";
	private static Connection connect;
	
	public static Connection getInstance() {
		if(connect == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+database, username, password);
			} catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return connect;
	}
}