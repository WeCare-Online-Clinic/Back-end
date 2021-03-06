package wecare.backend.model;

import javax.persistence.*;

@Entity
@Table(name="lab_test")
public class Test {

	@Id
    @SequenceGenerator(
            name = "test_id_seq",
            sequenceName = "test_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "test_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;

    @Column(name="field_1")
    private String field1;
    
    @Column(name="field_2")
    private String field2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Clinic getClinic() {	return clinic;}

	public void setClinic(Clinic clinic) {this.clinic = clinic;}

	@Override
	public String toString() {
		return "Test{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", clinic=" + clinic +
				", field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				'}';
	}


}
