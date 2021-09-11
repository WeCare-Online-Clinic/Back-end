package wecare.backend.model.dto;

import wecare.backend.model.Patient;

import java.util.Date;

public class PatientRegister {
    private Patient patient;
    private Date clinicDate;

    public Patient getPatient() {
        return patient;
    }

    public Date getClinicDate() {
        return clinicDate;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setClinicDate(Date clinicDate) {
        this.clinicDate = clinicDate;
    }
}
