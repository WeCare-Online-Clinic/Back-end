package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;

@Entity
@Table(name="clinic_schedule")
public class ClinicSchedule {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	@Column(name="id")
	private Integer id;
	
	@Column(name="day")
	private String day;
	
	@Column(name="time")
	private Time time;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
	private Clinic clinic;
		
	public Integer getId() {

		return id;
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
