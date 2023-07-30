package medicine;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;


public class MedicineDAO {

	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	int medicineid; 
	String medicinename;
	String medicineusage;
	String dosses;
	String frequency;
	String sideeffect;
	
	//add medicine
	public void addMed(medicine medObj)
	{
		medicinename = medObj.getMedicinename();
		medicineusage = medObj.getMedicineusage();
		dosses = medObj.getDosses();
		frequency = medObj.getFrequency();
		sideeffect = medObj.getSideeffect();
		
		try {
			//call getConnection() method from ConnectionManager class			
			con = ConnectionManager.getConnection(); 
			System.out.print("connected");
			//3. create statement
			String sql = "INSERT INTO medicine(medicinename,medicineusage,dosses,frequency,sideeffect)VALUES (?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, medicinename);
			ps.setString(2, medicineusage);
			ps.setString(3, dosses);
			ps.setString(4, frequency);
			ps.setString(5, sideeffect);
			
			System.out.println("SideEffect dao: " + sideeffect);
			
			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//get all med
	public static List<medicine> getAllMed()
	{
		List<medicine> medInfo = new ArrayList<medicine>();
		
		try {
			//call getConnection() method from ConnectionManager class			
			con = ConnectionManager.getConnection(); 
			
			//3. create statement
			String sql = "SELECT * FROM medicine ORDER BY medicinename";
			stmt = con.createStatement();
			
			//4. execute query
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				medicine medIn = new medicine();
				medIn.setMedicineid(rs.getInt("medicineid"));
				medIn.setMedicinename(rs.getString("medicinename"));
				medIn.setDosses(rs.getString("dosses"));
				medIn.setMedicineusage(rs.getString("medicineusage"));
				medIn.setFrequency(rs.getString("frequency"));
				medIn.setSideeffect(rs.getString("sideeffect"));
				medInfo.add(medIn);
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medInfo;
	}
	
	//get med by id
	public static medicine getMedByID(int medicineid) {
		medicine medIn = new medicine();
	try {
		//call getConnection() method from ConnectionManager class			
		con = ConnectionManager.getConnection(); 
		
		//3. create statement
		String sql = "SELECT * FROM medicine WHERE medicineid=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, medicineid);
		
		//4. execute query
		rs = ps.executeQuery();
		if(rs.next())
		{	
			medIn.setMedicineid(rs.getInt("medicineid"));
			medIn.setMedicinename(rs.getString("medicinename"));
			medIn.setMedicineusage(rs.getString("medicineusage"));
			medIn.setDosses(rs.getString("dosses"));
			medIn.setFrequency(rs.getString("frequency"));
			medIn.setSideeffect(rs.getString("sideeffect"));
			
		}
		
		//5. close connection
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return medIn;
	}
	
	//get med by name
	public static medicine getMedByName(String medicinename) {
		medicine medIn = new medicine();
	try {
		//call getConnection() method from ConnectionManager class			
		con = ConnectionManager.getConnection(); 
		
		//3. create statement
		String sql = "SELECT * FROM medicine WHERE name=?";
		ps = con.prepareStatement(sql);
		ps.setString(1, medicinename);
		
		//4. execute query
		rs = ps.executeQuery();
		if(rs.next())
		{
			medIn.setMedicineid(rs.getInt("medicineid"));
			medIn.setMedicinename(rs.getString("medicinename"));
			medIn.setDosses(rs.getString("dosses"));
			medIn.setMedicineusage(rs.getString("medicineusage"));
			medIn.setFrequency(rs.getString("frequency"));
			medIn.setSideeffect(rs.getString("sideeffect"));
			
		}
		
		//5. close connection
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return medIn;
	}
	
	//get med by usage
	public static medicine getMedByUsage(String medicineusage) {
		medicine medIn = new medicine();
	try {
		//call getConnection() method from ConnectionManager class			
		con = ConnectionManager.getConnection(); 
		
		//3. create statement
		String sql = "SELECT * FROM medicine WHERE medicineusage=?";
		ps = con.prepareStatement(sql);
		ps.setString(1, medicineusage);
		
		//4. execute query
		rs = ps.executeQuery();
		if(rs.next())
		{
			medIn.setMedicineid(rs.getInt("medicineid"));
			medIn.setMedicinename(rs.getString("medicinename"));
			medIn.setDosses(rs.getString("dosses"));
			medIn.setMedicineusage(rs.getString("medicineusage"));
			medIn.setFrequency(rs.getString("frequency"));
			medIn.setSideeffect(rs.getString("sideeffect"));
			
		}
		
		//5. close connection
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return medIn;
	}
	/*
	public static List<medicine> searchMedicine(String q){
		List<medicine> medicine = new ArrayList<medicine>();
		
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = q;
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
		return medicine;
	}*/
	
}
