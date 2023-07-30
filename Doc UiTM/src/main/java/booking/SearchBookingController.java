package booking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ConnectionManager;

/**
 * Servlet implementation class SearchBookingController
 */
@WebServlet("/SearchBookingController")
public class SearchBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null; 
	Statement stmt =null;
	ResultSet res = null;
	//String role ="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List <booking> booking = new ArrayList<booking>();
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = request.getParameter("q");
		String data=null;
		System.out.println(request.getParameter("q"));
		if(sql!=null)
		{
			data = "SELECT * FROM booking WHERE phonenumber like '%"+sql+"%' or purpose like '%"+sql+"%' or bookingdate like '%"+sql+"%' or bookingtime like '%"+sql+"%'or bookingid like '%"+sql+"%'";
		}
		
		res = stmt.executeQuery(data);
		if(res.next()){
			
			booking b = new booking();
			b.setBookingid(res.getInt("bookingid"));
			b.setPhonenumber(res.getString("phonenumber"));
			b.setPurpose(res.getString("purpose"));
			b.setBookingdate(res.getDate("bookingdate"));
			b.setBookingtime(res.getTime("bookingtime"));
			b.setPatientid(res.getString("patientid"));
			booking.add(b);
			System.out.println(res.getInt("bookingid"));
		
		}
		
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("bookings", booking);
		RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/BookList.jsp");
		view.forward(request, response);
	}

}
