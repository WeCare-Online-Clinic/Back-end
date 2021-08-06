package wecare.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "patient_clinic_data")
public class PatientClinicData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name="id")
    private Integer id;

    @Column(name = "note")
    private String note;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "lab_tests")
    private String labTests;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private ClinicAppointment clinicAppointment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public String getNote(){ return  note; }
    public String getPrescription(){ return  prescription; }
    public String getLabTests(){ return  labTests; }
    public ClinicAppointment getClinicAppointment(){ return clinicAppointment; }
    public Doctor getDoctor(){ return  doctor; }

}
