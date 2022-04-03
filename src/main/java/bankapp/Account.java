package bankapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bankapp.Login.Type;

public class Account {
	public enum Type{
		savings,
		current,
	};
	
	public enum Status{
		active,
		inactive,
	}
	
	public Integer customer_ID = null;
	public Integer user_ID = null;
	public String account_number = null;
	public BigDecimal balance = new BigDecimal("0.00").setScale(2, RoundingMode.FLOOR);
	public Type account_type = Type.current;
	public String iban = "";
	public Status status = Status.active;
	
	public Account() {
		
	}
	
	public static List<Account> findAccounts(int customer_ID) throws SQLException {
		List<Account> l = new ArrayList<Account>();
		
		Database.connect();
		
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.ACCOUNTS + " where customer_ID='"+String.valueOf(customer_ID)+"'");
		
		while(rs.next()) {
			Account ac = new Account();
			ac.customer_ID = rs.getInt("customer_ID");
			ac.user_ID = rs.getInt("user_ID");
			ac.account_number = rs.getString("account_number");
			ac.balance = rs.getBigDecimal("balance");
			ac.account_type = Type.valueOf(rs.getString("account_type"));
			ac.iban = rs.getString("iban");
			ac.status = Status.valueOf(rs.getString("status"));
			l.add(ac);
		}
		return l;
	}
	
	public static Account getAccount(int account_number) throws SQLException {
		Account ac = null;
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.ACCOUNTS + " where account_number='"+String.valueOf(account_number)+"'");
		
		while(rs.next()) {
			ac = new Account();
			ac.customer_ID = rs.getInt("customer_ID");
			ac.user_ID = rs.getInt("user_ID");
			ac.account_number = rs.getString("account_number");
			ac.balance = rs.getBigDecimal("balance");
			ac.account_type = Type.valueOf(rs.getString("account_type"));
			ac.iban = rs.getString("iban");
			ac.status = Status.valueOf(rs.getString("status"));
		}
		return ac;
	}
	
	
}
