

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * Servlet implementation class movingmoney
 */
@WebServlet("/movingmoney")
public class movingmoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movingmoney() {
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
				request.setAttribute("login", l);
				request.setAttribute("accounts", Account.findAccounts(l.customer_ID));
				request.getRequestDispatcher("movingmoney.jsp").forward(request, response);
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
//		payee: 15475749
//		account: 49462406
//		amount: 50
//		tandc: on
		
		String from = request.getParameter("payee");
		String to = request.getParameter("account");
		String amount = request.getParameter("amount");
		
		if(from.equals(to)) {
			response.sendError(HttpServletResponse.SC_CONFLICT, "Accounts cannot be the same");
			return;
		}
		
		try {
			BigDecimal money = BigDecimal.valueOf(Double.parseDouble(amount)).setScale(2, RoundingMode.FLOOR);
			
			System.out.println(money.toString());
			
			Account f = Account.getAccount(from);
			Account t = Account.getAccount(to);
					
			
			if(f != null && t != null) {
				if(f.balance.compareTo(money) > 0) {
					//Balance ok
					Transaction.createLocalBankTransaction(money, f, t, "Local Account Transfer", "Local Account Transfer");
					response.sendRedirect("movingmoney");
					
				}
				else {
					//balance too low
					response.sendError(HttpServletResponse.SC_CONFLICT, "Balance not enough to transfer money");
					return;
				}
			}
			else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Account Not Found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NumberFormatException n){
			response.sendError(HttpServletResponse.SC_CONFLICT, "Number not found");
			return;
		}
		
		
		
		
		
//		Account.sendBetweenAccounts(Integer.valueOf(from), Integer.valueOf(to), );
		
	}

}
