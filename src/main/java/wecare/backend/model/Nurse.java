package wecare.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nurse")
public class Nurse {


    @Id
    @SequenceGenerator(
            name = "nurse_id_seq",
            sequenceName = "nurse_id_seq",
            allocationSize = 1,
            initialValue = 1000
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nurse_id_seq"
    )

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "nurse_id")
    private String nurseId;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "is_head")
    private Boolean isHead;

    @Column(name = "registered_date")
    private Date registeredDate;

    @Column(name = "status")
    private Boolean status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @OneToMany(targetEntity = NurseSchedule.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_id", referencedColumnName = "id")
    private List<NurseSchedule> nurseSchedule;

    public List<NurseSchedule> getNurseSchedule() {
        if (nurseSchedule == null) {
            nurseSchedule = new ArrayList<>();
        }
        return nurseSchedule;
    }

    public void setNurseSchedule(List<NurseSchedule> nurseSchedule) {

        this.nurseSchedule = nurseSchedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {

        this.contact = contact;
    }

    public Boolean getIsHead() {
        return isHead;
    }

    public void setIsHead(Boolean isHead) {
        this.isHead = isHead;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Boolean getHead() {
        return isHead;
    }

    public void setHead(Boolean head) {
        isHead = head;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nurse [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", isHead=" + isHead + ", clinicId="
                + clinic.getName() + ", nurseSchedule=" + nurseSchedule + "]";
    }
}
