package wecare.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientReportDTO {

    @JsonProperty("reportId")
    private Integer reportId;

    @JsonProperty("field1")
    private float field1;

    @JsonProperty("field2")
    private float field2;

    @Type(type="org.hibernate.type.BinaryType")
    private byte[] pdfReport;

    @JsonProperty("reportId")
    public Integer getReportId() {
        return reportId;
    }

    @JsonProperty("reportId")
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    @JsonProperty("field1")
    public float getField1() {
        return field1;
    }

    @JsonProperty("field1")
    public void setField1(float field1) {
        this.field1 = field1;
    }

    @JsonProperty("field2")
    public float getField2() {
        return field2;
    }

    @JsonProperty("field2")
    public void setField2(float field2) {
        this.field2 = field2;
    }

    public byte[] getPdfReport() {
        return pdfReport;
    }

    public void setPdfReport(byte[] pdfReport) {
        this.pdfReport = pdfReport;
    }

    @Override
    public String toString() {
        return "PatientReportDTO{" +
                "reportId=" + reportId +
                ", field1=" + field1 +
                ", field2=" + field2 +
                ", pdfReport=" + pdfReport +
                '}';
    }
}

