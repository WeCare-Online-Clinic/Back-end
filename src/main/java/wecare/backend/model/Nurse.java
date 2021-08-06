package wecare.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="nurse")
public class Nurse {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name = "gender")
	private Character gender;

	@Column(name="email")
	private String email;

	@Column(name="contact")
	private Integer contact;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="is_head")
	private Boolean isHead;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
	private Clinic clinic;

	@OneToMany(targetEntity =  NurseSchedule.class,cascade = CascadeType.ALL)
	@JoinColumn(name="nurse_id",referencedColumnName = "id")
	private List<NurseSchedule> nurseSchedule;

	public List<NurseSchedule> getNurseSchedule() {
		if(nurseSchedule == null) {
			nurseSchedule = new ArrayList<>();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getContact() {
		return contact;
	}
	public void setContact(Integer contact) {
		this.contact = contact;
	}
	public Boolean getIsHead() {
		return isHead;
	}
	public void setIsHead(Boolean isHead) {
		this.isHead = isHead;
	}
	public Clinic getClinic() {
		return clinic;
	}

	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", isHead=" + isHead + ", clinicId="
				+ clinic.getName() + ", nurseSchedule=" + nurseSchedule + "]";
	}
}
