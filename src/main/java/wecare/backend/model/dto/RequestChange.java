package wecare.backend.model.dto;

import wecare.backend.model.ClinicDate;
import wecare.backend.model.PatientRequest;

import java.util.List;

public class RequestChange {
    private List<PatientRequest> requestList;
    private List<ClinicDate> availableDates;

    public List<PatientRequest> getRequestList() {
        return requestList;
    }

    public List<ClinicDate> getAvailableDates() {
        return availableDates;
    }

    public void setRequestList(List<PatientRequest> requestList) {
        this.requestList = requestList;
    }

    public void setAvailableDates(List<ClinicDate> availableDates) {
        this.availableDates = availableDates;
    }
}
