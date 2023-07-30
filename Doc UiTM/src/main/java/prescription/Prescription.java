package prescription;

import java.sql.Date;
import java.sql.Time;

import login.users;
import medicalstaff.medicalstaff;
import medicine.medicine;
import patients.patient;

public class Prescription {
	
	public Prescription() {
		
	}
	
	private String description;
	private int medicineid;
	int pid;
	String patientid;
	Date presdate;
	Time prestime;
	String medstaffid;
	medicine medicine;
	patient patient;
	medicalstaff staff;
	users user;
	
	
	public users getUser() {
		return user;
	}
	public void setUser(users user) {
		this.user = user;
	}
	public String getMedstaffid() {
		return medstaffid;
	}
	public void setMedstaffid(String medstaffid) {
		this.medstaffid = medstaffid;
	}
	public medicalstaff getStaff() {
		return staff;
	}
	public void setStaff(medicalstaff staff) {
		this.staff = staff;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(medicine medicine) {
		this.medicine = medicine;
	}
	public patient getPatient() {
		return patient;
	}
	public void setPatient(patient patient) {
		this.patient = patient;
	}
	
	public Date getPresdate() {
		return presdate;
	}
	public void setPresdate(Date presdate) {
		this.presdate = presdate;
	}
	public Time getPrestime() {
		return prestime;
	}
	public void setPrestime(Time prestime) {
		this.prestime = prestime;
	}
	public int getMedicineid() {
		return medicineid;
	}
	public void setMedicineid(int medicineid) {
		this.medicineid = medicineid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	
	
}


