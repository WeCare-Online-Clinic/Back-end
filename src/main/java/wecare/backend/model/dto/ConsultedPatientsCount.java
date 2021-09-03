package wecare.backend.model.dto;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ConsultedPatientsCount {

    private LocalDate clinicDate;
    private Integer count;

    public LocalDate getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(LocalDate clinicDate) {
        this.clinicDate = clinicDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

 ;
}
