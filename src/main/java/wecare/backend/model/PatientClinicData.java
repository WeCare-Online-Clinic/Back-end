package wecare.backend.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "patient_clinic_data")
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
public class PatientClinicData {

    @Id
    @SequenceGenerator(
            name = "patient_clinic_data_id_seq",
            sequenceName = "patient_clinic_data_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_clinic_data_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @Column(name = "note")
    private String note;

    @Column(name = "diagnosis")
    private String diagnosis;

    @OneToMany(targetEntity = Prescription.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_clinic_data_id", referencedColumnName = "id")
    private List<Prescription> prescription;

    @Column(name = "lab_tests")
    private String labTests;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private ClinicAppointment clinicAppointment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public String getNote(){

        return  note;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Prescription> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    public String getLabTests(){

        return  labTests;
    }

    public void setLabTests(String labTests) {
        this.labTests = labTests;
    }

    public ClinicAppointment getClinicAppointment(){

        return clinicAppointment;
    }

    public void setClinicAppointment(ClinicAppointment clinicAppointment) {
        this.clinicAppointment = clinicAppointment;
    }

    public Doctor getDoctor(){

        return  doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
