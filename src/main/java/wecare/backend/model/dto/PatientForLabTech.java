package wecare.backend.model.dto;


import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class PatientForLabTech {


    private String nic;

    private String name;

    private Character gender;

    private Integer contact;

    private String email;

    private List<Integer> clinics;

    public List<String> getClinicNames() { return clinicNames; }

    public void setClinicNames(List<String> clinicNames) {this.clinicNames = clinicNames; }

    private List<String> clinicNames;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getClinics() {
        return clinics;
    }

    public void setClinics(List<Integer> clinics) {
        this.clinics = clinics;
    }

    @Override
    public String toString() {
        return "PatientForLabTech{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", contact=" + contact +
                ", email='" + email + '\'' +
                ", clinics=" + clinics +
                ", clinicNames=" + clinicNames +
                '}';
    }
}
