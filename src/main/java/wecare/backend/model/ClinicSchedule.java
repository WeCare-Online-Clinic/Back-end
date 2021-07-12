package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clinic_schedule")
public class ClinicSchedule {

	@Id		
	@Column(name="ID")
	private Integer id;
	
	@Column(name="Clinic_ID")
	private Integer clinicId;
	
	@Column(name="Clinic_Name")
	private String clinicName;
	
	@Column(name="Day")
	private String day;
	
	@Column(name="Time")
	private String time;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClinicId() {
		return clinicId;
	}
	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
