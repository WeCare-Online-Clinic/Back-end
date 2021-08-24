package wecare.backend.model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "lab_report")
public class Report {

    @Column(name="ID")
    private Integer ID;

    @Column(name = "test_date")
    private Date test_date;

    @Column(name = "test_time")
    private Time test_time;

    @Column(name = "availability")
    private String availability;

    @Column(name = "issued_date")
    private Date issued_date;

    @Column(name = "data")
    private String data;

    @Column(name = "test_id")
    private Integer test_id;

    @Column(name = "patient_id")
    private Integer patient_id;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Date getTest_date() {
		return test_date;
	}

	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}

	public Time getTest_time() {
		return test_time;
	}

	public void setTest_time(Time test_time) {
		this.test_time = test_time;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Date getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(Date issued_date) {
		this.issued_date = issued_date;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getTest_id() {
		return test_id;
	}

	public void setTest_id(Integer test_id) {
		this.test_id = test_id;
	}

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	@Override
	public String toString() {
		return "Report [ID=" + ID + ", test_date=" + test_date + ", test_time=" + test_time + ", availability="
				+ availability + ", issued_date=" + issued_date + ", data=" + data + ", test_id=" + test_id
				+ ", patient_id=" + patient_id + "]";
	}

    
}
