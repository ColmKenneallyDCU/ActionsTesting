

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
import bankapp.Payee;
import bankapp.Transaction;

/**
 * Servlet implementation class transfers
 */
@WebServlet("/transfers")
public class transfers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfers() {
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
				request.setAttribute("accounts", Account.findAccounts(l.customer_ID));
				request.setAttribute("payees", Payee.getPayees(l.email));
				request.getRequestDispatcher("transfers.jsp").forward(request, response);
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
		
//		payee: 8
//		account: 15475749
//		amount: 1
//		tandc: on
		
		
		
		try {
			
			String payee_id = request.getParameter("payee");
			String account_number = request.getParameter("account");
			String amount = request.getParameter("amount");
			
			if(payee_id == null || account_number == null || amount == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not all fields inserted");
			}
			
			BigDecimal money = BigDecimal.valueOf(Double.parseDouble(amount)).setScale(2, RoundingMode.FLOOR);
			Payee p = Payee.getPayee(Integer.valueOf(payee_id));
			
			Account debitaccount = Account.getAccount(account_number);
			Account payeeaccount = Account.getAccount(p.payee_account);

			if(debitaccount == null || payeeaccount == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error Debit Account Not Found");
				return;
			}
			
			if(p.is_dcu_bank.equals(Payee.isDCU.Y)) {		
				if(debitaccount.balance.compareTo(money) > 0) {
					System.out.println("here7");
					Transaction.createLocalBankTransaction(money, debitaccount, payeeaccount, "TO "+payeeaccount.iban, "FROM "+debitaccount.iban);
					response.sendRedirect("transfers");					
				}
				else {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient Balance");
					return;
				}
			}
			else {
				if(debitaccount.balance.compareTo(money) > 0) {
					Transaction.createInternationalBankTransaction(money, debitaccount, payeeaccount, "TO "+payeeaccount.iban);
					response.sendRedirect("transfers");					
				}
				else {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient Balance");
					return;
				}
				
			}
			
			
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
