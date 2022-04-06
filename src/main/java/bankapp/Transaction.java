package bankapp;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
	
	public enum TranType{
		deposit,
		withdrawal,
		paymentin,
		paymentout,
	}
	
	public Integer transaction_id = null;
	public String account_number = null;
	public Integer user_ID = null;
	public Integer payee_id = null;
	public String payee_account = "";
	public String payee_iban = "";
	public Timestamp date_issued = null;
	public BigDecimal amount = new BigDecimal(0);
	public Type account_type = Account.Type.current;
	public TranType transaction_type = TranType.deposit;
	public String description = "";
	
	public Transaction() {
		
	}
	

	public static List<Transaction> findLastXTransactions(int user_id, int X) throws SQLException {
		List<Transaction> l = new ArrayList<Transaction>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.TRANSACTIONS + " where user_ID='"+String.valueOf(user_id)+"' ORDER BY date_issued DESC LIMIT " + String.valueOf(X));
		
		while(rs.next()) {
			Transaction t = new Transaction();
			t.transaction_id = rs.getInt("transaction_id");
			t.account_number = rs.getString("account_number");
			t.user_ID = rs.getInt("user_ID");
			t.payee_id = rs.getInt("payee_id");
			t.payee_account = rs.getString("payee_account");
			t.payee_iban = rs.getString("payee_iban");
			t.date_issued = rs.getTimestamp("date_issued");
			t.amount = rs.getBigDecimal("amount");
			t.account_type = Account.Type.valueOf(rs.getString("account_type"));
			t.transaction_type = TranType.valueOf(rs.getString("transaction_type"));
			t.description = rs.getString("description");
			l.add(t);
		}
		return l;
	}
	
	
	public static List<Transaction> findTransactions(int user_id) throws SQLException {
		List<Transaction> l = new ArrayList<Transaction>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.TRANSACTIONS + " where user_ID='"+String.valueOf(user_id)+"'");
		
		while(rs.next()) {
			Transaction t = new Transaction();
			t.transaction_id = rs.getInt("transaction_id");
			t.account_number = rs.getString("account_number");
			t.user_ID = rs.getInt("user_ID");
			t.payee_id = rs.getInt("payee_id");
			t.payee_account = rs.getString("payee_account");
			t.payee_iban = rs.getString("payee_iban");
			t.date_issued = rs.getTimestamp("date_issued");
			t.amount = rs.getBigDecimal("amount");
			t.account_type = Account.Type.valueOf(rs.getString("account_type"));
			t.transaction_type = TranType.valueOf(rs.getString("transaction_type"));
			t.description = rs.getString("description");
			l.add(t);
		}
		return l;
	}
	
	public static List<Transaction> getTransactionsBetweenDates(int account_number, String d1, String d2) throws SQLException{
		List<Transaction> l = new ArrayList<Transaction>();
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.TRANSACTIONS + " where account_number='"+String.valueOf(account_number)+"' and (date_issued between '" + d1 + "' and '" + d2 + "')");
		
		while(rs.next()) {
			Transaction t = new Transaction();
			t.transaction_id = rs.getInt("transaction_id");
			t.account_number = rs.getString("account_number");
			t.user_ID = rs.getInt("user_ID");
			t.payee_id = rs.getInt("payee_id");
			t.payee_account = rs.getString("payee_account");
			t.payee_iban = rs.getString("payee_iban");
			t.date_issued = rs.getTimestamp("date_issued");
			t.amount = rs.getBigDecimal("amount");
			t.account_type = Account.Type.valueOf(rs.getString("account_type"));
			t.transaction_type = TranType.valueOf(rs.getString("transaction_type"));
			t.description = rs.getString("description");
			l.add(t);
		}
		System.out.println(l.size());
		
		return l;
	}
	
	public static void createLocalBankTransaction(BigDecimal amount, Account a1, Account a2, String frommessage, String tomessage) throws SQLException{
		System.out.println("Account A1:" + a1.account_number);
		System.out.println("Account A2:" + a2.account_number);
		
		Database.connect();

		Timestamp datenow = new Timestamp(new Date().getTime());
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO " + Database.TRANSACTIONS + "(account_number,user_ID,payee_account,date_issued,amount,account_type,transaction_type,`description`) VALUES (?,?,?,?,?,?,?,?)");
		pstmt.clearParameters();
		pstmt.setString(1, a1.account_number);
		pstmt.setInt(2, a1.user_ID);
		pstmt.setString(3, a2.account_number);
		pstmt.setTimestamp(4, datenow);
		pstmt.setBigDecimal(5, amount);
		pstmt.setString(6, a1.account_type.toString());
		pstmt.setString(7, Transaction.TranType.withdrawal.toString());
		pstmt.setString(8, frommessage);
		pstmt.executeUpdate();

		pstmt = Database.con.prepareStatement("INSERT INTO " + Database.TRANSACTIONS + "(account_number,user_ID,payee_account,date_issued,amount,account_type,transaction_type,`description`) VALUES (?,?,?,?,?,?,?,?)");
		pstmt.clearParameters();
		pstmt.setString(1, a2.account_number);
		pstmt.setInt(2, a2.user_ID);
		pstmt.setString(3, a1.account_number);
		pstmt.setTimestamp(4, datenow);
		pstmt.setBigDecimal(5, amount);
		pstmt.setString(6, a2.account_type.toString());
		pstmt.setString(7, Transaction.TranType.deposit.toString());
		pstmt.setString(8, tomessage);
		pstmt.executeUpdate();
		

		a1.balance = a1.balance.subtract(amount);
		a2.balance = a2.balance.add(amount);
		
		pstmt = Database.con.prepareStatement("UPDATE "+ Database.ACCOUNTS +" SET balance=? where account_number=?");
		pstmt.clearParameters();
		pstmt.setBigDecimal(1, a1.balance);
		pstmt.setString(2, a1.account_number);
		pstmt.executeUpdate();

		pstmt = Database.con.prepareStatement("UPDATE "+ Database.ACCOUNTS +" SET balance=? where account_number=?");
		pstmt.clearParameters();
		pstmt.setBigDecimal(1, a2.balance);
		pstmt.setString(2, a2.account_number);
		pstmt.executeUpdate();
		
		return;
	}


	public static void createInternationalBankTransaction(BigDecimal amount, Account a1, Account a2, String frommessage) throws SQLException {
		Database.connect();
		Timestamp datenow = new Timestamp(new Date().getTime());
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO " + Database.TRANSACTIONS + "(account_number,user_ID,payee_account,date_issued,amount,account_type,transaction_type,`description`) VALUES (?,?,?,?,?,?,?,?)");
		pstmt.clearParameters();
		pstmt.setString(1, a1.account_number);
		pstmt.setInt(2, a1.user_ID);
		pstmt.setString(3, a2.account_number);
		pstmt.setTimestamp(4, datenow);
		pstmt.setBigDecimal(5, amount);
		pstmt.setString(6, a1.account_type.toString());
		pstmt.setString(7, Transaction.TranType.withdrawal.toString());
		pstmt.setString(8, frommessage);
		pstmt.executeUpdate();

		a1.balance = a1.balance.subtract(amount);
		
		pstmt = Database.con.prepareStatement("UPDATE "+ Database.ACCOUNTS +" SET balance=? where account_number=?");
		pstmt.clearParameters();
		pstmt.setBigDecimal(1, a1.balance);
		pstmt.setString(2, a1.account_number);
		pstmt.executeUpdate();
		return;
	}
	
}
