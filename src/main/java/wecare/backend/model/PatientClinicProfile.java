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
import java.util.Date;

@Entity
@Table(name = "patient_clinic_profile")
public class PatientClinicProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name="diagnosis")
    private String diagnosis;

    @Column(name="admission_date")
    private Date admissionDate;

    public Integer getId(){

        return id;
    }

    public Clinic getClinic(){

        return clinic;
    }

    public Patient getPatient(){

        return  patient;
    }

    public String getDiagnosis(){

        return  diagnosis;
    }

    public Date getAdmissionDate(){

        return admissionDate;
    }
}
