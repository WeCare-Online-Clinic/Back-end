package wecare.backend.model.dto;

import wecare.backend.model.Clinic;
import wecare.backend.model.Patient;

import java.util.Date;

public class PatientRegister {
    private Patient patient;
    private Clinic clinic;
    private Date clinicDate;

    public Patient getPatient() {
        return patient;
    }

    public Date getClinicDate() {
        return clinicDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setClinicDate(Date clinicDate) {
        this.clinicDate = clinicDate;
    }
}
