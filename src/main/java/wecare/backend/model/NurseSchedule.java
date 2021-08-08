package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="nurse_schedule")
public class NurseSchedule {
	@Id
	@SequenceGenerator(
			name = "nurse_schedule_id_seq",
			sequenceName = "nurse_schedule_id_seq",
			allocationSize = 1
	)

	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "nurse_id_seq"
	)

	@Column(name="id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="clinic_sid",referencedColumnName = "id")
	private ClinicSchedule clinicSchedule;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public ClinicSchedule getClinicSchedule(){

		return clinicSchedule;
	}

	public void setClinicSchedule(ClinicSchedule clinicSchedule){

		this.clinicSchedule = clinicSchedule;
	}

}
