

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankapp.Customer;
import bankapp.Login;

/**
 * Servlet implementation class profile
 */
@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
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
				request.setAttribute("customer", Customer.getCustomer(l.customer_ID));
				request.getRequestDispatcher("profile.jsp").forward(request, response);
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
//		doGet(request, response);
		
//		fname: Patrick
//		lname: OSullivan
//		email: PaddyOsullivan@gmail.com
//		Address: 46 Rainey St
//		number: +3534879300433
//		tandc: on
//		
		
		try {
			
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String Address = request.getParameter("Address");
			String number = request.getParameter("number");
			
			if(fname == null || lname == null || email == null || Address == null || number == null) {
				response.sendError(HttpServletResponse.SC_CONFLICT, "Not all fields sent");
				return;
			}
			Cookie cookies[] = request.getCookies();
			Login l = Login.getLoginFromCookies(cookies);
			
			if(l == null) {
				response.sendError(HttpServletResponse.SC_CONFLICT, "User Not Logged in");
				return;
			}
			
			Customer c = Customer.getCustomer(l.customer_ID);
			
			c.first_name = fname;
			c.last_name = lname;
			c.email = email;
			c.address_1 = Address;
			c.phone_num = number;
			
			c.updateCustomer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("profile");
		
	}

}
