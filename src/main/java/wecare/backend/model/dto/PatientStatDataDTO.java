package wecare.backend.model.dto;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PatientStatDataDTO {
    private Float data1;
    private Float data2;
    private LocalDate date;

    public Float getData1() {
        return data1;
    }

    public void setData1(Float data1) {
        this.data1 = data1;
    }

    public Float getData2() {
        return data2;
    }

    public void setData2(Float data2) {
        this.data2 = data2;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PatientStatDataDTO{" +
                "data1=" + data1 +
                ", data2=" + data2 +
                ", date=" + date +
                '}';
    }
}
