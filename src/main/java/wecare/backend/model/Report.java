package wecare.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lab_report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name="native",strategy = "native")
    @Column(name = "id")
    private Integer id;

    @Column(name = "test_date")
    private LocalDate testDate;

    @Column(name = "test_time")
    private Time testTime;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "data_1")
    private Float data1;
    
    @Column(name = "data_2")
    private Float data2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", referencedColumnName = "ID")
    private Test test;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Lob
    @Column(name="pdf_report")
    private byte[]  pdfReport;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
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

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Float getData1() {
		return data1;
	}

	public void setData1(Float data1) {
		this.data1 = data1;
	}

	public Float getData2() {
		return data2;
	}

	public void setData2(Float data2) {
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

	public byte[] getPdfReport() {
		return pdfReport;
	}

	public void setPdfReport(byte[] pdfReport) {
		this.pdfReport = pdfReport;
	}

	@Override
	public String toString() {
		return "Report{" +
				"id=" + id +
				", testDate=" + testDate +
				", testTime=" + testTime +
				", availability=" + availability +
				", issuedDate=" + issuedDate +
				", data1=" + data1 +
				", data2=" + data2 +
				", test=" + test +
				", patient=" + patient +
				", pdfReport=" + pdfReport +
				'}';
	}
}
