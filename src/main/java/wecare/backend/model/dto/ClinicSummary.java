package wecare.backend.model.dto;

import wecare.backend.model.ClinicAppointment;
import wecare.backend.model.ClinicDate;
import wecare.backend.model.PatientClinicProfile;

import java.util.List;

public class ClinicSummary {
    private ClinicDate clinicDate;

    private List<ClinicAppointment> visitedPatients;

    private List<ClinicAppointment> notVisitedPatients;

    public ClinicDate getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(ClinicDate clinicDate) {
        this.clinicDate = clinicDate;
    }

    public List<ClinicAppointment> getVisitedPatients() {
        return visitedPatients;
    }

    public void setVisitedPatients(List<ClinicAppointment> visitedPatients) {
        this.visitedPatients = visitedPatients;
    }

    public List<ClinicAppointment> getNotVisitedPatients() {
        return notVisitedPatients;
    }

    public void setNotVisitedPatients(List<ClinicAppointment> notVisitedPatients) {
        this.notVisitedPatients = notVisitedPatients;
    }
}
