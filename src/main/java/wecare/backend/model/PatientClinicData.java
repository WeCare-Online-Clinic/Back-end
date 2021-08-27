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

    @Type(type = "json")
    @Column(name = "prescription", columnDefinition = "jsonb")
    private final Map<String, List<Map<String, String>>> prescription;

    {
        prescription = new HashMap<>();
    }

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

    public Map<String, List<Map<String, String>>> getPrescription(){

        return  prescription;
    }

    public String getLabTests(){

        return  labTests;
    }

    public ClinicAppointment getClinicAppointment(){

        return clinicAppointment;
    }

    public Doctor getDoctor(){

        return  doctor;
    }

}
