package booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionManager;
import patients.patient;

public class BookingDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	int bookingid;
	String patientid;
	String phonenumber;
	String purpose;
	Date bookingdate;
	Time bookingtime;
	patient patient;
	
	//list all bookings
	public static List<booking> getAllBookings(){
		List <booking> booking = new ArrayList<booking>();
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = "SELECT * FROM booking";
		
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			
			booking b = new booking();
			b.setBookingid(rs.getInt("bookingid"));
			b.setPatientid(rs.getString("patientid"));
			b.setPhonenumber(rs.getString("phonenumber"));
			b.setPurpose(rs.getString("purpose"));
			b.setBookingdate(rs.getDate("bookingdate"));
			b.setBookingtime(rs.getTime("bookingtime"));
			booking.add(b);
			//System.out.println(res.getInt("bookingid"));
		
		}
		
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return booking;
	}
	
	//get booking by id
	public static booking getBookingById(int bookingid) {
		booking book = new booking();
		
		try {
			//call getConnection() method from ConnectionManager class			
			con = ConnectionManager.getConnection(); 
			
			//3. create statement
			String sql = "SELECT * FROM booking WHERE bookingid=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookingid);
			
			//4. execute query
			rs = ps.executeQuery();
			if(rs.next())
			{	
				book.setBookingid(rs.getInt("bookingid"));
				book.setPatientid(rs.getString("patientid"));
				book.setPhonenumber(rs.getString("phonenumber"));
				book.setPurpose(rs.getString("purpose"));
				book.setBookingdate(rs.getDate("bookingdate"));
				book.setBookingtime(rs.getTime("bookingtime"));
			}
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	//add booking
	public void addBooking(booking books) {
		bookingid = books.getBookingid();
		patientid = books.getPatientid();
		phonenumber = books.getPhonenumber();
		purpose = books.getPurpose();
		bookingdate = books.getBookingdate();
		bookingtime = books.getBookingtime();
		patient = books.getPatient();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "INSERT INTO booking(phonenumber,bookingdate,bookingtime,purpose,patientid)VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(sql);			
			ps.setString(1, phonenumber);	
			ps.setDate(2, bookingdate);
			ps.setTime(3, bookingtime);
			ps.setString(4, purpose);
			ps.setString(5, patientid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5.close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//update booking
	public void updateBooking(booking books) {
		bookingid = books.getBookingid();
		patientid = books.getPatientid();
		phonenumber = books.getPhonenumber();
		purpose = books.getPurpose();
		bookingdate = books.getBookingdate();
		bookingtime = books.getBookingtime();
		patient = books.getPatient();
		
		try {
			//call getConnection() method from ConnectionManager class
			con = ConnectionManager.getConnection();
			
			//3. create statement
			String sql = "UPDATE booking SET patientid=?, phonenumber=? , purpose=? , bookingdate=? , bookingtime=?  WHERE bookingid=?";
			ps = con.prepareStatement(sql);		
			ps.setString(1, patientid);
			ps.setString(2, phonenumber);
			ps.setString(3, purpose);
			ps.setDate(4, bookingdate);
			ps.setTime(5, bookingtime);
			ps.setInt(6, bookingid);
			
			//4. execute query
			ps.executeUpdate();
			
			//5.close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//delete booking
	public void deleteBooking(int bookingid) {
		try {
			
			//call getConnection() method from ConnectionManager class			
			con = ConnectionManager.getConnection(); 
			
			//3. create statement
			String sql = "DELETE FROM booking WHERE  bookingid=?"; 
			ps = con.prepareStatement(sql); 
			ps.setInt(1, bookingid);

			//4. execute query
			ps.executeUpdate();
			
			//5. close connection
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//list bookings by patientid
	public static List<booking> getAllBookingsByPatients(String patientid){
		List <booking> booking = new ArrayList<booking>();
		
		try {
		
		con = ConnectionManager.getConnection();
		
		String sql = "SELECT * FROM booking WHERE patientid=?";
		ps = con.prepareStatement(sql);
		ps.setString(1, patientid);
		
		rs = ps.executeQuery();
		while(rs.next()){
			
			booking b = new booking();
			b.setBookingid(rs.getInt("bookingid"));
			b.setPatientid(rs.getString("patientid"));
			b.setPhonenumber(rs.getString("phonenumber"));
			b.setPurpose(rs.getString("purpose"));
			b.setBookingdate(rs.getDate("bookingdate"));
			b.setBookingtime(rs.getTime("bookingtime"));
			booking.add(b);
			System.out.println("BookingDAO (list): "+rs.getInt("bookingid"));
		
		}
		
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return booking;
	}
	//search booking for staff
	public static List<booking> searchBookings(String q){
		List <booking> booking = new ArrayList<booking>();
		try {
		
		con = ConnectionManager.getConnection();
		
		stmt = con.createStatement();
		String sql = q;
		String data=null;
		
		System.out.println(q);
		if(sql!=null)
		{
			data = "SELECT * FROM booking WHERE phonenumber like '%"+sql+"%' or purpose like '%"+sql+"%' or bookingdate like '%"+sql+"%' or bookingtime like '%"+sql+"%'or bookingid like '%"+sql+"%'";
		}
		
		rs = stmt.executeQuery(data);
		if(rs.next()){
			
			booking b = new booking();
			b.setBookingid(rs.getInt("bookingid"));
			b.setPhonenumber(rs.getString("phonenumber"));
			b.setPurpose(rs.getString("purpose"));
			b.setBookingdate(rs.getDate("bookingdate"));
			b.setBookingtime(rs.getTime("bookingtime"));
			b.setPatientid(rs.getString("patientid"));
			booking.add(b);
			System.out.println(rs.getInt("bookingid"));
		
		}
		
		con.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return booking;
	}
	//search booking for patients
		public static List<booking> searchBookingsPatients(String q){
			List <booking> booking = new ArrayList<booking>();
			try {
			
			con = ConnectionManager.getConnection();
			
			stmt = con.createStatement();
			String sql = q;
			String data=null;
			
			System.out.println(q);
			if(sql!=null)
			{
				data = "SELECT * FROM booking WHERE phonenumber like '%"+sql+"%' or purpose like '%"+sql+"%' or bookingdate like '%"+sql+"%' or bookingtime like '%"+sql+"%'or bookingid like '%"+sql+"%'";
			}
			
			rs = stmt.executeQuery(data);
			if(rs.next()){
				
				booking b = new booking();
				b.setBookingid(rs.getInt("bookingid"));
				b.setPhonenumber(rs.getString("phonenumber"));
				b.setPurpose(rs.getString("purpose"));
				b.setBookingdate(rs.getDate("bookingdate"));
				b.setBookingtime(rs.getTime("bookingtime"));
				b.setPatientid(rs.getString("patientid"));
				booking.add(b);
				System.out.println(rs.getInt("bookingid"));
			
			}
			
			con.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return booking;
		}
	
	
}
