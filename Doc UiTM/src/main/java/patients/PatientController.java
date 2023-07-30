package patients;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.BookingDAO;
import prescription.PrescriptionDAO;

/**
 * Servlet implementation class PatientController
 */
@WebServlet("/PatientController")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PatientDAO dao;
    private PrescriptionDAO dao1;
    String action="";
    String forward="";
    String role="";
    RequestDispatcher view;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientController() {
        super();
        dao = new PatientDAO();
        dao1 = new PrescriptionDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		role = request.getParameter("role");

		if (action.equalsIgnoreCase("listPatient")) {
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward="MedicalStaff/PatientList.jsp";
				try {
					request.setAttribute("patients", PatientDAO.getAllPatients());
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (action.equalsIgnoreCase("viewPatient")) {
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
				forward = "MedicalStaff/ViewPrescription.jsp";
				String patientid = request.getParameter("patientid");
				try {
					request.setAttribute("patient", PatientDAO.getAllPatientsById(patientid));
					request.setAttribute("pres", PrescriptionDAO.getPrescriptionById(patientid));
					
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}


}
