package wecare.backend.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class PatientTest {

    private String patientNIC;
    private Integer testType;

    public String getPatientNIC() {
        return patientNIC;
    }

    public void setPatientNIC(String patientNIC) {
        this.patientNIC = patientNIC;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    @Override
    public String toString() {
        return "PatientTest{" +
                "patientNIC='" + patientNIC + '\'' +
                ", testType=" + testType +
                '}';
    }
}
