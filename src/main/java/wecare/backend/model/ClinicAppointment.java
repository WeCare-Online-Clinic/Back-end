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

@Entity
@Table(name = "clinic_appointment")

public class ClinicAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_did", referencedColumnName = "id")
    private ClinicDate clinicDate;

    @Column(name = "time")
    private Time time;

    @Column(name = "queue_no")
    private Integer queueNo;

    public Integer getId(){
        return id;
    }

    public Patient getPatient(){
        return patient;
    }

    public ClinicDate getClinicDate(){
        return clinicDate;
    }

    public Time getTime(){
        return time;
    }

    public Integer getQueueNo(){
        return queueNo;
    }

}
