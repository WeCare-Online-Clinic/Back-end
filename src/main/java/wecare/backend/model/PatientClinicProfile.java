package wecare.backend.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "patient_clinic_profile")
public class PatientClinicProfile {

    @Id
    @SequenceGenerator(
            name = "patient_clinic_profile_id_seq",
            sequenceName = "patient_clinic_profile_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_clinic_profile_id_seq"
    )

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

    public String getAdmissionDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(admissionDate);
    }
}
