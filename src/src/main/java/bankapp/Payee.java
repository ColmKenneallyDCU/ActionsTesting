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
	public isDCU is_dcu_bank = null;
	public String bank = "";
	public Integer dcu_user_id = null;
	public Integer dcu_customer_id = null;
	public String account_email = "";
	
	public Payee() {
		
	}
	
	public static List<Payee> getPayees(String account_email) throws SQLException{
		List<Payee> l = new ArrayList<Payee>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.PAYEE + " where account_email='"+account_email+"'");
		
		while(rs.next()) {
			Payee p = new Payee();
			p.payee_id = rs.getInt("payee_id");
			p.payee_name = rs.getString("payee_name");
			p.payee_account = rs.getString("payee_account");
			p.payee_iban = rs.getString("payee_iban");
			p.is_dcu_bank = Payee.isDCU.valueOf(rs.getString("is_dcu_bank"));
			p.bank = rs.getString("bank");
			p.dcu_user_id = rs.getInt("dcu_user_id");
			p.dcu_customer_id = rs.getInt("dcu_customer_id");
			p.account_email = rs.getString("account_email");
			l.add(p);
		}
		
		return l;
	}
	
	public static void addPayee(String Name, String IBAN, String BankName, Login LoggedUser) throws SQLException{
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.ACCOUNTS + " where iban='"+IBAN+"'");
		
		Account a = null;
		
		while(rs.next()) {
			a = new Account();
			a.customer_ID = rs.getInt("customer_ID");
			a.user_ID = rs.getInt("user_ID");
			a.account_number = rs.getString("account_number");
			a.balance = rs.getBigDecimal("balance");
			a.account_type = Account.Type.valueOf(rs.getString("account_type"));
			a.iban = rs.getString("iban");
			a.status = Account.Status.valueOf(rs.getString("status"));
		}
	
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO " + Database.PAYEE + "(payee_name, payee_account, payee_iban, is_dcu_bank , bank, dcu_user_id, dcu_customer_id, account_email) VALUES (?,?,?,?,?,?,?,?)");

		if(a != null) {
			pstmt.clearParameters();
			pstmt.setString(1, Name); 
			pstmt.setString(2, a.account_number); //???
			pstmt.setString(3, IBAN);
			pstmt.setString(4, Payee.isDCU.Y.toString());
			pstmt.setString(5, BankName);
			pstmt.setInt(6, LoggedUser.user_ID);
			pstmt.setInt(7, LoggedUser.customer_ID);
			pstmt.setString(8, LoggedUser.email);
			System.out.println("After : " + pstmt.toString());
			pstmt.executeUpdate();			
		}else {
			pstmt.clearParameters();
			pstmt.setString(1, Name); 
			pstmt.setString(2, null); //???
			pstmt.setString(3, IBAN);
			pstmt.setString(4, Payee.isDCU.N.toString());
			pstmt.setString(5, BankName);
			pstmt.setInt(6, LoggedUser.user_ID);
			pstmt.setInt(7, LoggedUser.customer_ID);
			pstmt.setString(8, LoggedUser.email);
			System.out.println("After : " + pstmt.toString());
			pstmt.executeUpdate();		
		}
		

	}
	
	public static Payee getPayee(int payee_id) throws SQLException {
		Payee p = new Payee();

		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.PAYEE + " where payee_id='"+String.valueOf(payee_id)+"'");
		
		while(rs.next()) {
			p = new Payee();
			p.payee_id = rs.getInt("payee_id");
			p.payee_name = rs.getString("payee_name");
			p.payee_account = rs.getString("payee_account");
			p.payee_iban = rs.getString("payee_iban");
			p.is_dcu_bank = Payee.isDCU.valueOf(rs.getString("is_dcu_bank"));
			p.bank = rs.getString("bank");
			p.dcu_user_id = rs.getInt("dcu_user_id");
			p.dcu_customer_id = rs.getInt("dcu_customer_id");
			p.account_email = rs.getString("account_email");
		}
		
		return p;
	}
	
	
}
