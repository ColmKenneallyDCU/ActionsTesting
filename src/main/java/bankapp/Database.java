package bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Database {
	public static boolean connected = false;
	
	public static String DATABASE_NAME = "bankofdcu";
	
	public static String USERNAME = "admin";
	public static String PASSWORD = "EE417Password";
	public static String URL = "jdbc:mysql://dbbankofdcu.cngyo8pqaq6c.eu-west-1.rds.amazonaws.com:3306/"+DATABASE_NAME+"?autoReconnect=true";
	
	public static String CUSTOMERS = DATABASE_NAME + ".customers";
	public static String LOGINDB = DATABASE_NAME + ".logindb";
	public static String ADMINS = DATABASE_NAME + ".admins";
	public static String ACCOUNTS = DATABASE_NAME + ".accounts";
	public static String PAYEE = DATABASE_NAME + ".payee";
	public static String TRANSACTIONS = DATABASE_NAME + ".transactions";
	
	public static Connection con = null;
	
	public static Connection connect() {
		if(Database.connected == true) {
			return con;
		}
		try {
			System.out.println("Connecting to the SSD Database......");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
		} catch (Exception e) {
			System.out.println("Error Occured in connect() Databse");
			e.printStackTrace();
			System.exit(0);
		}
		Database.connected = true;
		return con;
	}
}
