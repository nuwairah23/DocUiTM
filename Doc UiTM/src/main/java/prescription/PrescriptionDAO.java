package prescription;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import db.ConnectionManager;
import login.loginDAO;
import medicalstaff.MedicalStaffDAO;
import medicine.MedicineDAO;
import patients.PatientDAO;

public class PrescriptionDAO {
	
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	private String description;
	private static int  medicineid;
	String patientid;
	Date presdate;
	Time prestime;
	String medstaffid;
	
	//add prescription
		public void addPrescription(Prescription bean) {
			//get the prescription
			patientid = bean.getPatientid();
			medicineid = bean.getMedicineid();
			description = bean.getDescription();
			presdate = bean.getPresdate();
			prestime = bean.getPrestime();
			medstaffid = bean.getMedstaffid();
			
			try {			
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();			
				//3. create statement			
				String sql = "INSERT INTO prescription(patientid,medicineid,presdate,prestime,description,medstaffid)VALUES(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, patientid);
				ps.setInt(2, medicineid);
				ps.setDate(3, presdate);
				ps.setTime(4, prestime);	
				ps.setString(5, description);
				ps.setString(6, medstaffid);
				
				//4. execute query
				ps.executeUpdate();	
				//5. close connection
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//list prescription by patient id
		public static Prescription getPrescriptionById(String patientid) {
			Prescription pres = new Prescription();
			
			try {
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();			
				//3. create statement			
				
				String sql ="SELECT *FROM (((prescription RIGHT JOIN medicine ON medicine.medicineid = prescription.medicineid) RIGHT JOIN patient on patient.patientid = prescription.patientid) LEFT JOIN medicalstaff on medicalstaff.medstaffid = prescription.medstaffid) LEFT JOIN users on users.userid = medicalstaff.medstaffid where patient.patientid=?;";
				ps = con.prepareStatement(sql);
				ps.setString(1, patientid);
				//4. execute query
				rs = ps.executeQuery();
				
				if(rs.next()) {
					pres.setPid(rs.getInt("pid"));
					pres.setMedicineid(rs.getInt("medicineid"));
					pres.setPatientid(rs.getString("patientid"));
					pres.setPresdate(rs.getDate("presdate"));
					pres.setPrestime(rs.getTime("prestime"));
					pres.setDescription(rs.getString("description"));
					pres.setMedstaffid(rs.getString("medstaffid"));
					pres.setMedicine(MedicineDAO.getMedByID(rs.getInt("medicineid")));
					pres.setPatient(PatientDAO.getAllPatientsById(rs.getString("patientid")));
					pres.setStaff(MedicalStaffDAO.getStaffById(rs.getString("medstaffid")));
					pres.setUser(loginDAO.getUserById(rs.getString("userid")));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return pres;
			
		}
}
