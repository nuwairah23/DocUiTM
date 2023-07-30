package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private loginDAO dao;
	HttpSession session;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		dao = new loginDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//get the current session
			HttpSession session = request.getSession(true);
			//set current session to null.
			session.setAttribute("sessionUser", null);							
			//destroy session
			session.invalidate();			
			//redirect to login page						  
			response.sendRedirect("Login.jsp");			
		}catch (Throwable ex) {
			System.out.println(ex);
		}
	}	

}