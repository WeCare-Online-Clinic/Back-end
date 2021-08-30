package wecare.backend.model;


import javax.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @SequenceGenerator(
            name = "prescription_id_seq",
            sequenceName = "prescription_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "prescription_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "medicine")
    private String medicine;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "morning")
    private Float morning;

    @Column(name = "afternoon")
    private Float afternoon;

    @Column(name = "evening")
    private Float evening;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_clinic_data_id", referencedColumnName = "id")
    private PatientClinicData patientClinicData;

    public Integer getId() {
        return id;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getQuantity() {
        return quantity;
    }

    public Float getMorning() {
        return morning;
    }

    public Float getAfternoon() {
        return afternoon;
    }

    public Float getEvening() {
        return evening;
    }
}
