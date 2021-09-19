package wecare.backend.model.dto;

import wecare.backend.model.Clinic;
import wecare.backend.model.Patient;

import java.util.Date;

public class AddAppointment {
    private Patient patient;
    private Clinic clinic;
    private Date date;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
