package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private loginDAO dao;
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        dao = new loginDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String userid = request.getParameter("userid");
	     String password = request.getParameter("password");
	    
	     users user = new users();
	        
	     user.setUserid(userid); 
	     user.setPassword(password);
	     
	     String userValidate = loginDAO.authenticateUser(user);
	      
	        if(userValidate.equals("SUCCESS")) 
	        {
	        	session = request.getSession(true);
				
				session.setAttribute("sessionRole", user.getUsertype()); 
				System.out.println(user.getUsertype());
				
				session.setAttribute("sessionId", user.getUserid());
				System.out.println(user.getUserid());
				
				session.setAttribute("sessionName", user.getName());
				System.out.println(user.getName());
				
		      	if(user.getUsertype().equalsIgnoreCase("Student") || user.getUsertype().equalsIgnoreCase("UiTM Staff")) {
		      		request.setAttribute("user", loginDAO.getUserById(user.getUserid())); 
			        RequestDispatcher view = request.getRequestDispatcher("Patients/HomeP.jsp");
			      	view.forward(request, response);	
				}
				else {
					request.setAttribute("user", loginDAO.getUserById(user.getUserid()));   									
					RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/HomeS.jsp"); 	
					view.forward(request, response);	
	            
				}
	        }
	        else
	         {
	             request.setAttribute("errMessage", userValidate); 
	             RequestDispatcher view = request.getRequestDispatcher("/Login.jsp");
	             view.forward(request, response);
	         }
	    }
	}
