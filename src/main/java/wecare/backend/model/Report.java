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
    private Date testDate;

    @Column(name = "test_time")
    private Time testTime;

    @Column(name = "availability")
    private String availability;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "data")
    private String data;

    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "patient_id")
    private Integer patientId;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Time getTestTime() {
		return testTime;
	}

	public void setTestTime(Time testTime) {
		this.testTime = testTime;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "Report [ID=" + ID + ", testDate=" + testDate + ", testTime=" + testTime + ", availability="
				+ availability + ", issuedDate=" + issuedDate + ", data=" + data + ", testId=" + testId + ", patientId="
				+ patientId + "]";
	}

	
	
    
}
