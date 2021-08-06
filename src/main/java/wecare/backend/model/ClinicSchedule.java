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

import java.sql.Time;

@Entity
@Table(name="clinic_schedule")
public class ClinicSchedule {

	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
	private Clinic clinic;
	
	@Column(name="day")
	private String day;
	
	@Column(name="time")
	private Time time;
		
	public Integer getId() {
		return id;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}

}
