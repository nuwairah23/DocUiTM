package medicine;

public class medicine {
	
	private int medicineid; 
	private String medicinename;
	private String medicineusage;
	private String dosses;
	private String frequency;
	private String sideeffect;
	
	public medicine() {}
	
	public int getMedicineid() {
		return medicineid;
	}


	public void setMedicineid(int medicineid) {
		this.medicineid = medicineid;
	}


	public String getMedicinename() {
		return medicinename;
	}


	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}


	public String getMedicineusage() {
		return medicineusage;
	}


	public void setMedicineusage(String medicineusage) {
		this.medicineusage = medicineusage;
	}


	public String getSideeffect() {
		return sideeffect;
	}

	public void setSideeffect(String sideeffect) {
		this.sideeffect = sideeffect;
	}

	public void setDosses(String dosses) {
		this.dosses = dosses;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDosses() {
		return dosses;
	}
	
	
	

}
