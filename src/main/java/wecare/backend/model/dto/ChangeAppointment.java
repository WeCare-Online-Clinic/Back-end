package wecare.backend.model.dto;

import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicDate;
import wecare.backend.model.Patient;
import wecare.backend.model.PatientRequest;

import java.util.Date;

public class ChangeAppointment {
    private PatientRequest patientRequest;
    private Date date;

    public PatientRequest getPatientRequest() {
        return patientRequest;
    }

    public void setPatientRequest(PatientRequest patientRequest) {
        this.patientRequest = patientRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
