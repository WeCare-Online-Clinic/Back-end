package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = ClinicSchedule.class, cascade = CascadeType.ALL, mappedBy = "clinic")
    private List<ClinicSchedule> clinicSchedules;

    public List<ClinicSchedule> getClinicSchedules(){
        if(clinicSchedules == null){
            clinicSchedules = new ArrayList<>();
        }

        return clinicSchedules;
    }

    public Integer getId(){

        return id;
    }

    public String getName(){

        return  name;
    }

    public String getDescription(){

        return description;
    }
}
