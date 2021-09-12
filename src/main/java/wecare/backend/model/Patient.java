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

    @Column(name = "nic")
    private String nic;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Character gender;

	@Column(name="address")
	private String address;
	
	@Column(name="birthdate")
	private Date  birthdate;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "email")
    private String email;


    @Column(name = "registered_date")
    private Date registeredDate;
    
    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
	private Clinic clinic;

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


    public Date getRegisteredDate(){

        return  registeredDate;
    }

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Integer getContact(){

        return  contact;
    }
	public void setContact(Integer contact) {

		this.contact = contact;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}
	public String getNIC() {

		return nic;
	}

	public void setNIC(String nic) {

		this.nic = nic;
	}
	
	public Date getBirthdate() {

		return birthdate;
	}

	public void setBirthdate(Date birthdate) {

		this.birthdate = birthdate;
	}

	
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public Clinic getClinic() {

		return clinic;
	}
	
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", address=" + address + ", nic=" + nic + ", birthdate=" + birthdate + ", clinicId="+ clinic.getName() + "]";
	}

}

