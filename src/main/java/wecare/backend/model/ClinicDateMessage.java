package wecare.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "clinic_date_message")
public class ClinicDateMessage {
    @Id
    @SequenceGenerator(
            name = "clinic_date_message_id_seq",
            sequenceName = "clinic_date_message_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clinic_date_message_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_did", referencedColumnName = "id")
    private ClinicDate clinicDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id", referencedColumnName = "id")
    private Nurse nurse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClinicDate getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(ClinicDate clinicDate) {
        this.clinicDate = clinicDate;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}
