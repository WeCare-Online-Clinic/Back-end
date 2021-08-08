package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="nurse_schedule")
public class NurseSchedule {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
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
