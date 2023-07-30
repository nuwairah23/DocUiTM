package booking;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patients.PatientDAO;

/**
 * Servlet implementation class BookingController
 */
@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingDAO dao;
	String action="";
    String forward="";
    String role ="";
    RequestDispatcher view;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController() {
        super();
        dao = new BookingDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		role = request.getParameter("role");
		action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("viewBookings")) {
			if(role.equalsIgnoreCase("Student")||role.equalsIgnoreCase("UiTM Staff"))
				forward = "Patients/ViewBooking.jsp";
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin"))
				forward = "MedicalStaff/ViewBooking.jsp";
			
			int bookingid = Integer.parseInt(request.getParameter("bookingid"));
			request.setAttribute("booking", BookingDAO.getBookingById(bookingid));
		}
		if(action.equalsIgnoreCase("listBookings")) {
			if(role.equalsIgnoreCase("Student")||role.equalsIgnoreCase("UiTM Staff")) {
				forward = "Patients/BookList.jsp";
				String patientid = request.getParameter("patientid");
				System.out.println("Booking controller (list): "+patientid);
				request.setAttribute("bookings", BookingDAO.getAllBookingsByPatients(patientid));
			}
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward = "MedicalStaff/BookList.jsp";
				request.setAttribute("bookings", BookingDAO.getAllBookings());
			}
		}/*
		if(action.equalsIgnoreCase("searchBookings")) {
			if(role.equalsIgnoreCase("Student")||role.equalsIgnoreCase("UiTM Staff")) {
				forward = "Patients/BookList.jsp";
				String patientid = request.getParameter("patientid");
				System.out.println("Booking controller (list): "+patientid);
				request.setAttribute("bookings", BookingDAO.getAllBookingsByPatients(patientid));
			}
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward = "MedicalStaff/BookList.jsp";
				request.setAttribute("bookings", BookingDAO.getAllBookings());
			}
			
		}*/
		
		if(action.equalsIgnoreCase("deleteBookings")) {
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward = "MedicalStaff/BookList.jsp";
				int bookingid = Integer.parseInt(request.getParameter("bookingid"));
				dao.deleteBooking(bookingid);
				request.setAttribute("bookings", BookingDAO.getAllBookings());
			}
		}
		if(action.equalsIgnoreCase("updateBookings")) {
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward = "MedicalStaff/UpdateBooking.jsp";
				int bookingid = Integer.parseInt(request.getParameter("bookingid"));
				request.setAttribute("booking", BookingDAO.getBookingById(bookingid));
			}
		}
		if(action.equalsIgnoreCase("addBookings")) {
			forward = "Patients/AddBooking.jsp";
			request.setAttribute("bookings", BookingDAO.getAllBookings());
			try {
				request.setAttribute("patients", PatientDAO.getAllPatients());
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		booking book = new booking();
		
		book.setPatientid(request.getParameter("patientid"));
		book.setPhonenumber(request.getParameter("phonenumber"));
		book.setPurpose(request.getParameter("purpose"));
		book.setBookingdate(Date.valueOf(request.getParameter("bookingdate")));
		book.setBookingtime(Time.valueOf(LocalTime.parse(request.getParameter("bookingtime"))));
		
		String bookingid = request.getParameter("bookingid");
		
		if(bookingid==null || bookingid.isEmpty()) {
			dao.addBooking(book);
			request.setAttribute("bookings", BookingDAO.getAllBookingsByPatients(request.getParameter("patientid")));
			view = request.getRequestDispatcher("Patients/BookList.jsp");
			view.forward(request, response);
			
		}
		else {
			book.setBookingid(Integer.parseInt(bookingid));
			dao.updateBooking(book);
			request.setAttribute("bookings", BookingDAO.getAllBookings());
			view = request.getRequestDispatcher("MedicalStaff/BookList.jsp");
			view.forward(request, response);
		}
	}

}
