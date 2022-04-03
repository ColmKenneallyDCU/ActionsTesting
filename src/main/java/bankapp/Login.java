package bankapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.Cookie;

public class Login {
	
	enum Type{
		Y,
		N
	};
	
	public Integer customer_ID = null;
	public Integer user_ID = null;
	public String email = "";
	public String user_password = "";
	public Timestamp last_online = null;
	public Type is_admin = Type.N;
	
	public Login() {
		
	}
	
	public static Login getLoginFromCookies(Cookie cookies[]) throws SQLException {
		HashMap<String, Cookie> cookiemap = new HashMap<>();
		
		for(Cookie cookie: cookies)
			cookiemap.put(cookie.getName(), cookie);
		
		if(cookiemap.get("email") != null && cookiemap.get("password") != null) {
			String user = cookiemap.get("email").getValue();
			String pass = cookiemap.get("password").getValue();
			return Login.findLogin(user,pass);
		}
		return null;
	}
	
	public void setLoginTime() throws SQLException {
		Database.connect();
		Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
		PreparedStatement stmt = Database.con.prepareStatement("UPDATE " + Database.LOGINDB + " SET last_online=? WHERE user_ID=?");
		stmt.setTimestamp(1, updatedAt);
		stmt.setInt(2, user_ID);
		stmt.executeUpdate();
		last_online = updatedAt;
	}
	
	public static Login findLogin(String mail, String password) throws SQLException {
		Login l = null;
		
		Database.connect();
		ResultSet rs = Database.con.createStatement().executeQuery("SELECT * FROM " + Database.LOGINDB + " where email='"+mail+"' and user_password='" + password + "'");
		
		while(rs.next()) {
			l = new Login();
			l.customer_ID = rs.getInt("customer_ID");
			l.user_ID = rs.getInt("user_ID");
			l.email = rs.getString("email");
			l.user_password = rs.getString("user_password");
			l.last_online = rs.getTimestamp("last_online");
			l.is_admin = Type.valueOf(rs.getString("is_admin"));
		}
		return l;
	}
	
	
	
}
