package wecare.backend.model.dto;

import wecare.backend.model.PatientClinicData;

import java.util.Date;

public class ClinicDataPost {

    private PatientClinicData patientClinicData;

    private Date nextClinic;

    private Integer patientId;

    private Integer clinicDId;

    private Integer clinicId;

    public PatientClinicData getPatientClinicData() {
        return patientClinicData;
    }

    public Date getNextClinic() {
        return nextClinic;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getClinicDId() {
        return clinicDId;
    }

    public Integer getClinicId() {
        return clinicId;
    }
}
