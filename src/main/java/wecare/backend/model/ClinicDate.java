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

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="clinic_date")
public class ClinicDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
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
    private List<Integer> queue;

    @Column(name = "summary")
    private Map <String, String> summary;

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
    public List getQueue() {
        return queue;
    }
    public Map<String, String> getSummary() {
        return summary;
    }

}
