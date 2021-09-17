package wecare.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient_message")
public class PatientMessage {
    @Id
    @SequenceGenerator(
            name = "patient_message_id_seq",
            sequenceName = "patient_message_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_message_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name = "viewed")
    private Boolean viewed;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
