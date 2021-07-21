package wecare.backend.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "doctor_schedule")
public class DoctorShedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "Doctor_ID")
    private Integer id;

    @Column(name = "Clinic_SID")
    private Integer clinicSid;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClinicSid() {
        return clinicSid;
    }

    public void setClinicSid(Integer clinicSid) {
        this.clinicSid = clinicSid;
    }



}
