package bankapp;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bankapp.Account.Status;
import bankapp.Account.Type;

public class Transaction {
//	CREATE TABLE bankofdcu.transactions(
//	transaction_id INT NOT NULL AUTO_INCREMENT,
//	account_number INTEGER NOT NULL,
//	user_ID INTEGER NOT NULL,
//	payee_id INTEGER default null,
//	payee_account VARCHAR(50) default null,
//	payee_iban VARCHAR(50) default null,
//	date_issued TIMESTAMP,
//	amount DECIMAL(15,2) DEFAULT 0,
//	account_type ENUM('savings','current') NOT NULL,
//	transaction_type ENUM('deposit','wiithdrawal','paymentin','paymentout') NOT NULL,
//	`description` TEXT,
	
	enum Type{
		savings,
		current
	}
	
	enum TranType{
		deposit,
		withdrawal,
		paymentin,
		paymentout,
	}
	
	public int transaction_id = 0;
	public int account_number = 0;
	public int user_ID = 0;
	public int payee_id = 0;
	public String payee_account = "";
	public String payee_iban = "";
	public Timestamp date_issued = null;
	public BigDecimal amount = new BigDecimal(0);
	public Type account_type = Type.current;
	public TranType transaction_type = TranType.deposit;
	public String description = "";
	
	public Transaction() {
		
	}
	
	public static List<Transaction> findAccounts(int cust_id) throws SQLException {
		List<Transaction> l = new ArrayList<Transaction>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.TRANSACTIONS + " where customer_ID='"+String.valueOf(cust_id)+"'");
		
		while(rs.next()) {
			Transaction t = new Transaction();
			t.transaction_id = rs.getInt("transaction_id");
			t.account_number = rs.getInt("account_number");
			t.user_ID = rs.getInt("user_ID");
			t.payee_id = rs.getInt("payee_id");
			t.payee_account = rs.getString("payee_account");
			t.payee_iban = rs.getString("payee_iban");
			t.date_issued = rs.getTimestamp("date_issued");
			t.amount = rs.getBigDecimal("amount");
			t.account_type = Type.valueOf(rs.getString("account_type"));
			t.transaction_type = TranType.valueOf(rs.getString("transaction_type"));
			t.description = rs.getString("description");
			l.add(t);
		}
		return l;
	}
	
}
