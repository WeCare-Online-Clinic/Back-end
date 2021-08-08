package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="doctor_schedule")
public class DoctorSchedule {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="clinic_sid",referencedColumnName = "id")
	private ClinicSchedule clinicSchedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_id",referencedColumnName = "id", nullable = true)
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

