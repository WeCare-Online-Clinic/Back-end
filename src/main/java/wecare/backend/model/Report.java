package wecare.backend.model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "lab_report")
public class Report {

    @Column(name="ID")
    private Integer ID;

    @Column(name = "test_date")
    private Date test_date;

    @Column(name = "test_time")
    private Time test_time;

    @Column(name = "availability")
    private String availability;

    @Column(name = "issued_date")
    private Date issued_date;

    @Column(name = "data")
    private String data;

    @Column(name = "test_id")
    private Integer test_id;

    @Column(name = "patient_id")
    private Integer patient_id;

}
