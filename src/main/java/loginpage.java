

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import bankapp.Login;
import webapp.Account;

/**
 * Servlet implementation class loginPage
 */
@WebServlet("/loginpage")
public class loginpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Cookie cookies[] = request.getCookies();
			if(cookies != null) {
				Login l = Login.getLoginFromCookies(cookies);
				
				if(l != null) {
					response.sendRedirect("account");
					return;
				}
			}
			if(request.getParameter("email") != null && request.getParameter("password") != null) {
				doPost(request,response);
			}
			else{
				request.getRequestDispatcher("loginpage.jsp").forward(request, response);
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("*******************");
		System.out.println(email);
		System.out.println(password);
		
		try {
			Login l = Login.findLogin(email, password);
			
			if(l != null){
				l.setLoginTime();
				
				Cookie user = new Cookie("email", l.email);
				Cookie pass = new Cookie("password", l.user_password);
				user.setMaxAge(60*60);
				pass.setMaxAge(60*60);
				
				response.addCookie(user);
				response.addCookie(pass);

				response.sendRedirect("account");
			}
			else {
				System.out.println("No User Found");
				request.setAttribute("loginmessage", "Invalid Login Details");
				request.getRequestDispatcher("loginpage.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			System.out.println("Error in Login Post");
			e.printStackTrace();
		}
		
		
		
	}

}
