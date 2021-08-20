package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_id_seq",
            sequenceName = "patient_id_seq",
            allocationSize = 1,
            initialValue = 200000
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_seq"
    )

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
	
	@Column(name="nic")
	private String nic;

	@Column(name="address")
	private String address;
	
	@Column(name="clinic")
	private String clinic;

	@Column(name="birthdate")
	private Date  birthdate;

	
	
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
	
	public String getClinic() {

		return clinic;
	}

	public void setClinic(String clinic) {

		this.clinic = clinic;
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

	public String getAddr() {

		return address;
	}

	public void setAddr(String address) {

		this.address = address;
	}
	public String getNIC() {

		return nic;
	}

	public void setNIC(String nic) {

		this.nic = nic;
	}
	
	public Date getDOB() {

		return birthdate;
	}

	public void setDOB(Date birthdate) {

		this.birthdate = birthdate;
	}

	
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", address=" + address + ", nic=" + nic + ", birthdate=" + birthdate +" ,clinic=" + clinic + "]";
	}
}
