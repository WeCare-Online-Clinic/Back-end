package wecare.backend.model.dto;

public class LabTechDataCard {

    private  String name;
    private  Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LabTechDataCard{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
