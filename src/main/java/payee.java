

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
import bankapp.Payee;
import bankapp.Transaction;

/**
 * Servlet implementation class payee
 */
@WebServlet("/payee")
public class payee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Cookie cookies[] = request.getCookies();
		
		try {
			Login l = Login.getLoginFromCookies(cookies);
			if(l != null) {
				request.getRequestDispatcher("payee.jsp").forward(request, response);
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
		
		String payeename = request.getParameter("payee");
		String iban = request.getParameter("Iban");
		String bankname = request.getParameter("bankname");
		
		Cookie cookies[] = request.getCookies();
		try {
			Login l = Login.getLoginFromCookies(cookies);
			if(l != null) {
				for(Payee p : Payee.getPayees(l.email)) {
					if(p.payee_iban.equals(iban)){
						response.sendError(HttpServletResponse.SC_FORBIDDEN, "Payee Already Exists");
						return;
					}
				}
				
				Payee.addPayee(payeename, iban, bankname, l);
				response.sendRedirect("payee");
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "User Is not Logged in.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
