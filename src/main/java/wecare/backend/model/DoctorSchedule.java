package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="doctor_schedule")
public class DoctorSchedule {
	
	@Id	
	@SequenceGenerator(
			name = "doctor_schedule_id_seq",
			sequenceName = "doctor_schedule_id_seq",
			allocationSize = 1
	)

	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "doctor_schedule_id_seq"
	)

	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="clinic_sid",referencedColumnName = "id")
	private ClinicSchedule clinicSchedule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="doctor_id", referencedColumnName = "id")
	private Doctor doctor;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public ClinicSchedule getClinicSchedule() {

		return clinicSchedule;
	}

	public void setClinicSchedule(ClinicSchedule clinicSchedule) {

		this.clinicSchedule = clinicSchedule;
	}

}

