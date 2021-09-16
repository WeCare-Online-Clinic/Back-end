package wecare.backend.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class PatientReportDTO {

    private Integer reportId;

    private float field1;

    private float field2;

    private String pdf;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public float getField1() {
        return field1;
    }

    public void setField1(float field1) {
        this.field1 = field1;
    }

    public float getField2() {
        return field2;
    }

    public void setField2(float field2) {
        this.field2 = field2;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "PatientReportDTO{" +
                "reportId=" + reportId +
                ", field1=" + field1 +
                ", field2=" + field2 +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}
