package wecare.backend.model.dto;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
public class PatientStatTitleDTO {

    private String testName;
    private String field1;
    private String field2;
    private List<PatientStatDataDTO> dataList;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public List<PatientStatDataDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<PatientStatDataDTO> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PatientStatTitleDTO{" +
                "testName='" + testName + '\'' +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
