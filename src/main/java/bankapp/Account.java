package bankapp;

import java.math.BigDecimal;
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
	
	public int customer_ID = 0;
	public int user_ID = 0;
	public int account_number = 0;
	public BigDecimal balance = new BigDecimal("0.00");
	public Type account_type = Type.current;
	public String iban = "";
	public Status status = Status.active;
	
	public Account() {
		
	}
	
	public static List<Account> findAccounts(int cust_id) throws SQLException {
		List<Account> l = new ArrayList<Account>();
		
		Database.connect();
		
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.ACCOUNTS + " where customer_ID='"+String.valueOf(cust_id)+"'");
		
		while(rs.next()) {
			Account ac = new Account();
			ac.customer_ID = rs.getInt("customer_ID");
			ac.user_ID = rs.getInt("user_ID");
			ac.account_number = rs.getInt("account_number");
			ac.balance = rs.getBigDecimal("balance");
			ac.account_type = Type.valueOf(rs.getString("account_type"));
			ac.iban = rs.getString("iban");
			ac.status = Status.valueOf(rs.getString("status"));
			l.add(ac);
		}
		return l;
	}
}
