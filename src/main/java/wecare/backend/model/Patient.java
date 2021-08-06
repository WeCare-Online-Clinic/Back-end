package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy = "native")
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

    @Column(name = "registered_date")
    private Date registeredDate;

    public Integer getId(){ return id; }
    public String getNic(){ return  nic; }
    public Character getGender() { return gender; }
    public Date getBirthdate(){ return  birthdate; }
    public Integer getContact(){ return  contact; }
    public String getEmail(){ return email; }
    public Date getRegisteredDate(){ return  registeredDate; }
    public String getName(){ return  name; }

}

