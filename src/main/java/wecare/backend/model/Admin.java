package wecare.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @SequenceGenerator(
            name = "admin_id_seq",
            sequenceName = "admin_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_id_seq"
    )

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Integer getId(){

        return  id;
    }

    public String getName(){

        return  name;
    }
}
