package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity
@Table(name="clinic_schedule")
public class ClinicSchedule {

	@Id
	@SequenceGenerator(
			name = "clinic_schedule_id_seq",
			sequenceName = "clinic_schedule_id_seq",
			allocationSize = 1
	)

	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "clinic_schedule_id_seq"
	)

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
	public String getTime() {

		return new SimpleDateFormat("kk.mm").format(time);
	}
	public void setTime(Time time) {

		this.time = time;
	}

}
