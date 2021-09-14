package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "message")
public class Message {

    @Id
    @SequenceGenerator(
            name = "message_id_seq",
            sequenceName = "message_id_seq",
            allocationSize = 1,
            initialValue = 200000
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
	private Clinic clinic;
    
    @OneToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "patient_id", referencedColumnName = "id")
   	private Patient patient;
    
    @OneToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
   	private Doctor doctor;
    
    @OneToOne(fetch = FetchType.EAGER)
   	@JoinColumn(name = "nurse_id", referencedColumnName = "id")
   	private Nurse nurse;

    public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	
	public Clinic getClinic() {

		return clinic;
	}
	
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	
	public Patient getPatient() {

		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Nurse getNurse() {

		return nurse;
	}
	
	public void setNurse(Nurse  nurse) {
		this.nurse = nurse;
	}
	
	public Doctor getDoctor() {

		return doctor;
	}
	
	public void setDoctor(Doctor  doctor) {
		this.doctor = doctor;
	}
	

}

