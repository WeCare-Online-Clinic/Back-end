package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="clinic_date")
public class ClinicDate {

    @Id
    @SequenceGenerator(
            name = "clinic_date_id_seq",
            sequenceName = "clinic_date_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clinic_date_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_sid", referencedColumnName = "id")
    private ClinicSchedule clinicSchedule;

    @Column(name="date")
    private Date date;

    @Column(name="no_patients")
    private Integer noPatients;

    @Column(name = "queue")
    private Integer[] queue;

    @Column(name = "summary")
    private String summary;

    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {

        this.id = id;
    }
    public ClinicSchedule getClinicSchedule() {

        return clinicSchedule;
    }
    public Date getClinicDate() {

        return date;
    }
    public Integer getNoPatients() {

        return noPatients;
    }
    public Integer[] getQueue() {

        return queue;
    }
    public String getSummary() {

        return summary;
    }

}
