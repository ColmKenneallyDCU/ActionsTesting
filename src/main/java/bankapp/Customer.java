package bankapp;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankapp.Account.Status;
import bankapp.Account.Type;

public class Customer {
	public enum EMPL {
		Y,
		N,
	};
	
	public Integer customer_ID = null;
	public String first_name = "";
	public String middle_name = "";
	public String last_name = "";
	public Date date_of_birth = null;
	public String address_1 = "";
	public String address_2 = "";
	public String address_3 = "";
	public String city = "";
	public String county = "";
	public String phone_num = "";
	public String email = "";
	public EMPL employed = Customer.EMPL.Y;
	
	public Customer(){
		
	}
	
	public static Customer getCustomer(int customer_ID) throws SQLException {
		Customer c = null;
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.CUSTOMERS + " where customer_ID='"+String.valueOf(customer_ID)+"'");
		
		while(rs.next()) {
			c = new Customer();
			c.customer_ID = rs.getInt("customer_ID");
			c.first_name = rs.getString("first_name");
			c.middle_name = rs.getString("middle_name");
			c.last_name = rs.getString("last_name");
			c.date_of_birth = rs.getDate("date_of_birth");
			c.address_1 = rs.getString("address_1");
			c.address_2 = rs.getString("address_2");
			c.address_3 = rs.getString("address_3");
			c.city = rs.getString("city");
			c.county = rs.getString("county");
			c.phone_num = rs.getString("phone_num");
			c.email = rs.getString("email");
			c.employed = Customer.EMPL.valueOf(rs.getString("employed"));
		}
		
		return c;
	}
	
	//VALUES (?,?,?,?,?,?,?,?) (first_name,last_name,date_of_birth,address_1,city,county,phone_num,email)
	public void updateCustomer() throws SQLException {
		Database.connect();
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE "+ Database.CUSTOMERS +" SET first_name=?, last_name=?, date_of_birth=?, address_1=?, city=?, county=?, phone_num=?, email=?  where customer_ID=?");
		pstmt.clearParameters();
		pstmt.setString(1, this.first_name);
		pstmt.setString(2, this.last_name);
		pstmt.setDate(3, this.date_of_birth);
		pstmt.setString(4, this.address_1);
		pstmt.setString(5, this.city);
		pstmt.setString(6, this.county);
		pstmt.setString(7, this.phone_num);
		pstmt.setString(8, this.email);
		pstmt.setInt(9, this.customer_ID);
		
		pstmt.executeUpdate();			

		
	}
	
}
