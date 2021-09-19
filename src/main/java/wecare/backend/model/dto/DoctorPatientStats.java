package wecare.backend.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class DoctorPatientStats {

    private Integer clinicId;
    private Integer patientId;

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "DoctorPatientStats{" +
                "clinicId=" + clinicId +
                ", patientId=" + patientId +
                '}';
    }
}
