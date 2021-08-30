package wecare.backend.model;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        ),
        @TypeDef(
                name = "json",
                typeClass = JsonType.class
        )
})
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

    @Type(type = "list-array")
    @Column(
            name = "queue",
            columnDefinition = "integer[]"
    )
    private List<Integer> queue;

    @Type(type = "json")
    @Column(name = "summary", columnDefinition = "jsonb")
    private Map<String, String> summary = new HashMap<>();

    @Column(name = "no_patients")
    private Integer noPatients;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "started")
    private Boolean started;

    @Column(name = "curr_queue")
    private Integer currQueue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id", referencedColumnName = "id")
    private Nurse nurse;

    public ClinicDate() {
    }

    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {

        this.id = id;
    }
    public ClinicSchedule getClinicSchedule() {

        return clinicSchedule;
    }

    public void setClinicSchedule(ClinicSchedule clinicSchedule) {
        this.clinicSchedule = clinicSchedule;
    }

    public String getDate() {

        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return new SimpleDateFormat("kk.mm").format(startTime);
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return new SimpleDateFormat("kk.mm").format(endTime);
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Integer getNoPatients() {
        return noPatients;
    }

    public void setNoPatients(Integer noPatients) {
        this.noPatients = noPatients;
    }

    public Boolean getStarted() {
        return  started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Integer getCurrQueue() {
        return currQueue;
    }

    public void setCurrQueue(Integer currQueue) {
        this.currQueue = currQueue;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public List<Integer> getQueue() {

        return queue;
    }

    public void setQueue(List<Integer> queue) {
        this.queue = queue;
    }

    public Map<String, String> getSummary() {

        return summary;
    }

    public void setSummary(Map<String, String> summary) {
        this.summary = summary;
    }
}
