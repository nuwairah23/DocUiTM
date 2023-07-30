package medicine;

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
 * Servlet implementation class medListController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null; 
	Statement stmt =null;
	ResultSet rs = null;
	String role="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		role = request.getParameter("role");
		List<medicine> medicine = new ArrayList<medicine>();
		
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = request.getParameter("q");
		String data=null;
		
		if(sql!=null)
		{
			data = "SELECT * from medicine where medicinename like '%"+sql+"%' or medicineusage like '%"+sql+"%' or sideeffect like '%"+sql+"%'";
		}
		
		rs = stmt.executeQuery(data);
		if(rs.next()){
			medicine m = new medicine();
			m.setMedicineid(rs.getInt("medicineid"));
			m.setMedicinename(rs.getString("medicinename"));
			m.setMedicineusage(rs.getString("medicineusage"));
			m.setDosses(rs.getString("dosses"));
			m.setFrequency(rs.getString("frequency"));
			m.setSideeffect(rs.getString("sideeffect"));
			medicine.add(m);
		}
		
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(role.equalsIgnoreCase("Student")||role.equalsIgnoreCase("UiTM Staff")) {
			request.setAttribute("medicines", medicine);
			RequestDispatcher view = request.getRequestDispatcher("Patients/SearchListMed.jsp");
			view.forward(request, response);
		}
		if(role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Admin")) {
			request.setAttribute("medicines", medicine);
			RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/SearchListMed.jsp");
			view.forward(request, response);
		}
	}
		

}
