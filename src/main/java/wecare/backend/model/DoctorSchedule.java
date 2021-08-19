package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "doctor_schedule")
public class DoctorSchedule {

	@Id
	@SequenceGenerator(name = "doctor_schedule_id_seq", sequenceName = "doctor_schedule_id_seq", allocationSize = 1)

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_schedule_id_seq")

	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_sid", referencedColumnName = "id")
	private ClinicSchedule clinicSchedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private Doctor doctor;

	public DoctorSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "DoctorSchedule [id=" + id + ", clinicSchedule=" + clinicSchedule + ", doctor=" + doctor + "]";
	}
}