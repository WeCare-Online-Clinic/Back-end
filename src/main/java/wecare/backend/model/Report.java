package wecare.backend.model;

import javax.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lab_report")
public class Report {

	@Id
    @SequenceGenerator(
            name = "report_id_seq",
            sequenceName = "report_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_id_seq"
    )

    @Column(name = "id")
    private Integer id;

    @Column(name = "test_date")
    private Date testDate;

    @Column(name = "test_time")
    private Time testTime;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "data_1")
    private Integer data1;
    
    @Column(name = "data_2")
    private Integer data2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "ID")
    private Test test;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Integer getData1() {
		return data1;
	}

	public void setData1(Integer data1) {
		this.data1 = data1;
	}

	public Integer getData2() {
		return data2;
	}

	public void setData2(Integer data2) {
		this.data2 = data2;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", testDate=" + testDate + ", testTime=" + testTime + ", availability="
				+ availability + ", issuedDate=" + issuedDate + ", data1=" + data1 + ", data2=" + data2 + ", test="
				+ test + ", patient=" + patient + "]";
	}

   
    
}
