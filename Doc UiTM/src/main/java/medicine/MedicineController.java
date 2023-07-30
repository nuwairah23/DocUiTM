package medicine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MedicineController
 */
@WebServlet("/MedicineController")
public class MedicineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MedicineDAO dao;
	String action="";
    String forward="";
    String role="";
    RequestDispatcher view;
    HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineController() {
        super();
        dao = new MedicineDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
		role = request.getParameter("role");
		
		if(action.equalsIgnoreCase("listMedicine")) {
			if(role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("UiTM Staff"))
				forward = "Patients/SearchListMed.jsp";
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin"))
				forward = "MedicalStaff/SearchListMed.jsp";
			
			request.setAttribute("medicines", MedicineDAO.getAllMed());
		}
		if(action.equalsIgnoreCase("viewMedicine")) {
			if(role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("UiTM Staff"))
				forward = "Patients/ViewMed.jsp";
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin"))
				forward = "MedicalStaff/ViewMed.jsp";
			
			int medicineid = Integer.parseInt(request.getParameter("medicineid"));
			request.setAttribute("medicine", MedicineDAO.getMedByID(medicineid));
		}
		if(action.equalsIgnoreCase("addMedicine")) {
			if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin"))
				forward = "MedicalStaff/AddMedicine.jsp";
			
			request.setAttribute("medicines", MedicineDAO.getAllMed());
		}
		
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		medicine medicine = new medicine();
		
		medicine.setMedicinename(request.getParameter("medicinename"));
		medicine.setMedicineusage(request.getParameter("medicineusage"));
		medicine.setDosses(request.getParameter("dosses"));
		medicine.setFrequency(request.getParameter("frequency"));
		medicine.setSideeffect(request.getParameter("sideeffect"));
		
		String medicineid = request.getParameter("medicineid");
		
		if (medicineid == null || medicineid.isEmpty()) 
			dao.addMed(medicine);
		
		request.setAttribute("medicines", MedicineDAO.getAllMed());
		view = request.getRequestDispatcher("MedicalStaff/SearchListMed.jsp");
		view.forward(request, response);
	}

}
