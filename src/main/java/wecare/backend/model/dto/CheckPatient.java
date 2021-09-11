package wecare.backend.model.dto;

import wecare.backend.model.Patient;
import wecare.backend.model.PatientClinicProfile;

public class CheckPatient {
    private Patient patient;
    private PatientClinicProfile patientClinicProfile;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientClinicProfile getPatientClinicProfile() {
        return patientClinicProfile;
    }

    public void setPatientClinicProfile(PatientClinicProfile patientClinicProfile) {
        this.patientClinicProfile = patientClinicProfile;
    }
}
