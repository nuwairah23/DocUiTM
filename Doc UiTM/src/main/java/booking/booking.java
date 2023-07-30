package booking;
import java.sql.Date;
import java.sql.Time;

import patients.patient;

public class booking {
	int bookingid;
	String patientid;
	String phonenumber;
	String purpose;
	Date bookingdate;
	Time bookingtime;
	patient patient;
	
	
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public patient getPatient() {
		return patient;
	}
	public void setPatient(patient patient) {
		this.patient = patient;
	}
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}
	public Time getBookingtime() {
		return bookingtime;
	}
	public void setBookingtime(Time bookingtime) {
		this.bookingtime = bookingtime;
	}
	
}
