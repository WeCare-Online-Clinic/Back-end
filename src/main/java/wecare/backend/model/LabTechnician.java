package wecare.backend.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "lab_technician")
public class LabTechnician {

    @Id
    @SequenceGenerator(
            name = "lab_technician_id_seq",
            sequenceName = "lab_technician_id_seq",
            allocationSize = 1,
            initialValue = 200
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lab_technician_id_seq"
    )

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "email")
    private String email;

    public Integer getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public Character getGender(){

        return  gender;
    }

    public Integer getContact() {

        return contact;
    }

    public String getEmail() {

        return email;
    }
}
