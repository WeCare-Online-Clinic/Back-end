package wecare.backend.model.dto;

import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicAppointment;

public class NextClinic {
    private ClinicAppointment clinicAppointment;
    private Clinic clinic;

    public ClinicAppointment getClinicAppointment() {
        return clinicAppointment;
    }

    public void setClinicAppointment(ClinicAppointment clinicAppointment) {
        this.clinicAppointment = clinicAppointment;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}