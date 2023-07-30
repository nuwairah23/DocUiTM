package medicalstaff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;

public class MedicalStaffDAO {

	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	String medstaffid;
	String email;
	String name;
	String phonenumber;
	
	//list all medical staff
	public static List<medicalstaff> getAllStaff()
	{
		 List<medicalstaff> medstaffs = new ArrayList<medicalstaff>();
		
		try {
			//call getConnection() method from ConnectionManager class			
			con = ConnectionManager.getConnection(); 
			
			//3. create statement
			String sql = "SELECT * FROM medicalstaff WHERE role=\"Doctor\" ORDER BY medstaffid;";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				medicalstaff staff = new medicalstaff();
				staff.setMedstaffid(rs.getString("medstaffid"));
				staff.setName(rs.getString("name"));
				staff.setEmail(rs.getString("email"));
				staff.setPhonenumber(rs.getString("phonenumber"));
				medstaffs.add(staff);
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medstaffs;
	}
		
		//list medical staff by id
		public static medicalstaff getStaffById(String medstaffid) {
			medicalstaff staff = new medicalstaff();
			
			try {
				//call getConnection() method from ConnectionManager class
				con = ConnectionManager.getConnection();			
				//3. create statement			
				
				String sql ="SELECT * FROM medicalstaff WHERE medstaffid =?";
				ps = con.prepareStatement(sql);
				ps.setString(1, medstaffid);
				//4. execute query
				rs = ps.executeQuery();
				
				if(rs.next()) {
					staff.setMedstaffid(rs.getString("medstaffid"));
					staff.setName(rs.getString("name"));
					staff.setEmail(rs.getString("email"));
					staff.setPhonenumber(rs.getString("phonenumber"));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return staff;
			
		}
}

