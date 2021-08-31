package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "clinic_appointment")

public class ClinicAppointment {

    @Id
    @SequenceGenerator(
            name = "clinic_appointment_id_seq",
            sequenceName = "clinic_appointment_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clinic_appointment_id_seq"
    )

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

    @Column(name = "visited")
    private Boolean visited;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient(){
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ClinicDate getClinicDate(){
        return clinicDate;
    }

    public void setClinicDate(ClinicDate clinicDate) {
        this.clinicDate = clinicDate;
    }

    public String getTime(){
        return new SimpleDateFormat("kk.mm").format(time);    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getQueueNo(){
        return queueNo;
    }

    public void setQueueNo(Integer queueNo) {
        this.queueNo = queueNo;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }
}
