package prescription;

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

import medicalstaff.MedicalStaffDAO;
import medicine.MedicineDAO;
import patients.PatientDAO;
/**
 * Servlet implementation class AddPrescriptionController
 */
@WebServlet("/AddPrescriptionController")
public class AddPrescriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrescriptionDAO dao;
	private MedicineDAO dao1;
	private PatientDAO dao2;
	private MedicalStaffDAO dao3;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPrescriptionController() {
        super();
        dao = new PrescriptionDAO();
        dao1 = new MedicineDAO();
        dao2 = new PatientDAO();
        dao3 = new MedicalStaffDAO();
        
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
		String patientid = request.getParameter("patientid");
		System.out.println(request.getParameter("patientid"));
		try {
			request.setAttribute("patient", PatientDAO.getAllPatientsById(patientid));
			request.setAttribute("medicines", MedicineDAO.getAllMed());
			request.setAttribute("medstaffs", MedicalStaffDAO.getAllStaff());
			request.setAttribute("pres", PrescriptionDAO.getPrescriptionById(patientid));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/AddPrescription.jsp");
		view.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Prescription prescription = new Prescription();
		System.out.println(request.getParameter("patientid"));
		LocalTime prestime1 = LocalTime.parse(request.getParameter("prestime"));
		Time prestime = Time.valueOf(prestime1);
		prescription.setPatientid(request.getParameter("patientid"));
		prescription.setMedicineid(Integer.parseInt(request.getParameter("medicineid")));
		prescription.setPresdate(Date.valueOf(request.getParameter("presdate")));
		prescription.setPrestime(prestime);
		prescription.setDescription(request.getParameter("description"));
		prescription.setMedstaffid(request.getParameter("medstaffid"));
		try {
			prescription.setPatient(PatientDAO.getAllPatientsById(request.getParameter("patientid")));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prescription.setMedicine(MedicineDAO.getMedByID(Integer.parseInt(request.getParameter("medicineid"))));
		prescription.setStaff(MedicalStaffDAO.getStaffById(request.getParameter("medstaffid")));
			
		dao.addPrescription(prescription);
		
		RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/PatientList.jsp");
		view.forward(request, response);
	}

}
