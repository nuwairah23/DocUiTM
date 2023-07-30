package patients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.ConnectionManager;

/**
 * Servlet implementation class SearchPatientController
 */
@WebServlet("/SearchPatientController")
public class SearchPatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null; 
	Statement stmt =null;
	ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPatientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List <patient> patient = new ArrayList<patient>();
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = request.getParameter("q");
		String data=null;
		
		if(sql!=null)
		{
			data = "SELECT * FROM patient WHERE patientid like '%"+sql+"%' or patientname like '%"+sql+"%'";
		}
		
		rs = stmt.executeQuery(data);
		if(rs.next()){
			
			patient p = new patient();
		
			p.setPatientid(rs.getString("patientid"));
			p.setPatientname(rs.getString("patientname"));
			p.setGender(rs.getString("gender"));
			p.setBirthdate(rs.getDate("birthdate"));
			p.setHeight(rs.getInt("height"));
			p.setWeight(rs.getInt("weight"));
			p.setBloodtype(rs.getString("bloodtype"));
			Blob blob = rs.getBlob("profilepic");
			InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
                 
            while ((bytesRead = inputStream.read(buffer)) != -1) {
            	outputStream.write(buffer, 0, bytesRead);                  
            }
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
            inputStream.close();
            outputStream.close();
            p.setBase64Image(base64Image);	
			patient.add(p);
		
		}
		
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("patients", patient);
		RequestDispatcher view = request.getRequestDispatcher("MedicalStaff/PatientList.jsp");
		view.forward(request, response);
	}


}
