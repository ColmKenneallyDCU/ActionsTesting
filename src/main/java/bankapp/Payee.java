package bankapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bankapp.Account.Status;
import bankapp.Account.Type;

public class Payee {
	
	public enum isDCU {
			Y,
			N,
	}
	
	public Integer payee_id = null;
	public String payee_name = "";
	public String payee_account = "";
	public String payee_iban = "";
	public String bank = "";
	public Integer dcu_user_id = null;
	public Integer dcu_customer_id = null;
	public String account_email = "";
	
	public Payee() {
		
	}
	
	public static List<Payee> getPayees(int customer_ID) throws SQLException{
		List<Payee> l = new ArrayList<Payee>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.PAYEE + " where customer_ID='"+String.valueOf(customer_ID)+"'");
		
		while(rs.next()) {
			Payee p = new Payee();
			p.payee_id = rs.getInt("payee_id");
			p.payee_name = rs.getString("payee_name");
			p.payee_account = rs.getString("payee_account");
			p.payee_iban = rs.getString("payee_iban");
			p.bank = rs.getString("bank");
			p.dcu_user_id = rs.getInt("dcu_user_id");
			p.dcu_customer_id = rs.getInt("dcu_customer_id");
			p.account_email = rs.getString("account_email");
			l.add(p);
		}
		
		return l;
	}
	
	public static void addPayee(String Name, String IBAN, String Email) throws SQLException{
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.LOGINDB + " where email='"+Email+"'");
		
		Login l = null;
		
		while(rs.next()) {
			l = new Login();
			l.customer_ID = rs.getInt("customer_ID");
			l.user_ID = rs.getInt("user_ID");
			l.email = rs.getString("email");
			l.user_password = rs.getString("user_password");
			l.last_online = rs.getTimestamp("last_online");
			l.is_admin = Login.Type.valueOf(rs.getString("is_admin"));
			
		}
//		
//		PreparedStatement pstmt = null;
//		
//		if(l != null) {
//			pstmt = Database.con.prepareStatement("INSERT INTO " + Database.TRANSACTIONS + "(payee_name, payee_account, payee_iban, bank, dcu_user_id, dcu_customer_id, account_email) VALUES (?,?,?,?,?,?,?)");
//			pstmt.clearParameters();
//			pstmt.setString(1, payee_name); 
//			pstmt.setString(2, payee_account);
//			pstmt.setString(3, payee_iban);
//			pstmt.setString(4, bank);
//			pstmt.setString(5, bank);
//			pstmt.setInt(6, dcu_user_id);
//			pstmt.setInt(7, dcu_customer_id);
//			
//			pstmt.executeUpdate();			
//		}else {
//			
//		}
		

	}
	
	
}
