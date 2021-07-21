package wecare.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="nurse")
public class Nurse {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="ID")
	private Integer id;
	

	@Column(name="First_Name")	
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="NIC")
	private String nic;
	
	@Column(name="Mobile")
	private Integer mobile;	
	
	@Column(name="Qualification")
	private String qualification;
	
	@Column(name="Type")
	private Integer type;
	
	@Column(name="Clinic_ID")
	private Integer clinicId;	
	


	@OneToMany(targetEntity =  NurseSchedule.class,cascade = CascadeType.ALL)
	@JoinColumn(name="Nurse_ID",referencedColumnName = "id")
	private List<NurseSchedule> nurseSchedule;
		
	
	
	public List<NurseSchedule> getNurseSchedule() {
		if(nurseSchedule==null) {
			nurseSchedule =new ArrayList<>();
		}
		return nurseSchedule;
	}
	public void setNurseSchedule(List<NurseSchedule> nurseSchedule) {
		this.nurseSchedule = nurseSchedule;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getClinicId() {
		return clinicId;
	}
	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}
	
	@Override
	public String toString() {
		return "Nurse [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", nic="
				+ nic + ", mobile=" + mobile + ", qualification=" + qualification + ", type=" + type + ", clinicId="
				+ clinicId + ", nurseSchedule=" + nurseSchedule + "]";
	}
}
