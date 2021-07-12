package wecare.backend.model.dto;

import java.io.Serializable;

public class ClinicSheduleRequest  {
	
	private String clinic;
	
	private Integer clinicID;

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	
	public Integer getClinicID() {
		return clinicID;
	}

	public void setClinicID(Integer clinicID) {
		this.clinicID = clinicID;
	}

	@Override
	public String toString() {
		return "ClinicSheduleRequest [clinic=" + clinic + ", clinicID=" + clinicID + "]";
	}

	
	
	
	

}
