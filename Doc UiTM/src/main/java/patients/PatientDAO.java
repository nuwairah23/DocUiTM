package patients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import db.*;


public class PatientDAO {
	
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	String patientid;
	
		//get all patient
		public static List<patient>getAllPatients() throws SQLException, IOException{
			List<patient> patients = new ArrayList<patient>();
			
			try {			
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();			
				//3. create statement			
				String sql = "SELECT * FROM patient ORDER BY patientid";
				stmt = con.createStatement();			
				
				//4. execute query
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
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
					
					patients.add(p);
				}	
				
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}				
			
			return patients;
		}
		public static patient getAllPatientsById(String patientid) throws SQLException, IOException{
			patient patient = new patient();
			
			try {			
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();			
				//3. create statement			
				String sql = "SELECT * FROM patient WHERE patientid=?";
				stmt = con.createStatement();			
				
				ps = con.prepareStatement(sql);
				ps.setString(1, patientid);			
				//4. execute query
				rs = ps.executeQuery();
				if(rs.next()) {
					
					patient.setPatientid(rs.getString("patientid"));
					patient.setPatientname(rs.getString("patientname"));
					patient.setGender(rs.getString("gender"));
					patient.setBirthdate(rs.getDate("birthdate"));
					patient.setHeight(rs.getInt("height"));
					patient.setWeight(rs.getInt("weight"));
					patient.setBloodtype(rs.getString("bloodtype"));
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
		            patient.setBase64Image(base64Image);
					
				}	
				
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}				
			
			return patient;
		}
}
