package wecare.backend.model.dto;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ChangeClinicDate {

    private Integer clinicId;
    private LocalDate CurrentClinicDate;

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public LocalDate getCurrentClinicDate() {
        return CurrentClinicDate;
    }

    public void setCurrentClinicDate(LocalDate currentClinicDate) {
        CurrentClinicDate = currentClinicDate;
    }

    @Override
    public String toString() {
        return "ChangeClinicDate{" +
                "clinicId=" + clinicId +
                ", CurrentClinicDate=" + CurrentClinicDate +
                '}';
    }

}
