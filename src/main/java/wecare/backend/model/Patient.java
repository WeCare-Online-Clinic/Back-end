package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_id_seq",
            sequenceName = "patient_id_seq",
            allocationSize = 1,
            initialValue = 200000
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_seq"
    )

    @Column(name="id")
    private Integer id;

    @Column(name = "nic")
    private String nic;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "registered_date")
    private Date registeredDate;

    public Integer getId(){

        return id;
    }

    public String getNic(){

        return  nic;
    }

    public Character getGender() {

        return gender;
    }

    public Date getBirthdate(){

        return  birthdate;
    }

    public Integer getContact(){

        return  contact;
    }

    public String getEmail(){

        return email;
    }

    public String getAddress(){

        return address;
    }

    public Date getRegisteredDate(){

        return  registeredDate;
    }

    public String getName(){

        return  name;
    }

}

