

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankapp.Account;
import bankapp.Login;
import bankapp.Transaction;

/**
 * Servlet implementation class bankstatement
 */
@WebServlet("/bankstatement")
public class bankstatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bankstatement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookies[] = request.getCookies();
		
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String acc_num = request.getParameter("account");
		
		try {
			Login l = Login.getLoginFromCookies(cookies);
			if(l != null) {
				request.setAttribute("login", l);
				request.setAttribute("accounts", Account.findAccounts(l.customer_ID));
				
				if(acc_num != null)
					request.setAttribute("transactions", Transaction.getTransactionsBetweenDates(Integer.valueOf(acc_num), sdate, edate));
				request.getRequestDispatcher("bankstatement.jsp").forward(request, response);
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "User Is not Logged in.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
