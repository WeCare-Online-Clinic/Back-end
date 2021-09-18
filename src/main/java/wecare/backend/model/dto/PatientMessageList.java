package wecare.backend.model.dto;

import wecare.backend.model.ClinicDateMessage;
import wecare.backend.model.ClinicMessage;
import wecare.backend.model.PatientMessage;

import java.util.List;

public class PatientMessageList {
    private List<PatientMessage> patientMessages;
    private List<ClinicMessage> clinicMessages;
    private List<ClinicDateMessage> clinicDateMessages;

    public List<PatientMessage> getPatientMessages() {
        return patientMessages;
    }

    public void setPatientMessages(List<PatientMessage> patientMessages) {
        this.patientMessages = patientMessages;
    }

    public List<ClinicMessage> getClinicMessages() {
        return clinicMessages;
    }

    public void setClinicMessages(List<ClinicMessage> clinicMessages) {
        this.clinicMessages = clinicMessages;
    }

    public List<ClinicDateMessage> getClinicDateMessages() {
        return clinicDateMessages;
    }

    public void setClinicDateMessages(List<ClinicDateMessage> clinicDateMessages) {
        this.clinicDateMessages = clinicDateMessages;
    }
}
