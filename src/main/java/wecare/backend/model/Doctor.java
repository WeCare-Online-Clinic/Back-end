package wecare.backend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
public class Doctor {
		@Id
		@SequenceGenerator(
				name = "doctor_id_seq",
				sequenceName = "doctor_id_seq",
				allocationSize = 1
		)

		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "doctor_id_seq"
		)

		@Column(name="id")
		private Integer id;

		@Column(name="name")
		private String name;

		@Column(name="email")
		private String email;
		
		@Column(name="doctor_id")
		private String doctorId;
		
		@Column(name="contact")
		private Integer contact;
		
		@Column(name="qualification")
		private String qualification;
		
		@Column(name="specialization")
		private String specialization;

		@OneToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "clinic_id", referencedColumnName = "id")
		private Clinic clinic;

		@OneToMany(targetEntity = DoctorSchedule.class, cascade = CascadeType.ALL)
		@JoinColumn(name="doctor_id",referencedColumnName = "id")
		private List<DoctorSchedule> doctorSchedules;

		public List<DoctorSchedule> getDoctorSchedules(){
			if(doctorSchedules == null){
				doctorSchedules = new ArrayList<>();
			}
			return  doctorSchedules;
		}

		public void setDoctorSchedules(List<DoctorSchedule> doctorSchedules){

			this.doctorSchedules = doctorSchedules;
		}

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
		public String getEmail() {

			return email;
		}

		public void setEmail(String email) {

			this.email = email;
		}

		public String getDoctorId() {

			return doctorId;
		}

		public void setDoctorId(String doctorId) {

			this.doctorId = doctorId;
		}

		public Integer getContact() {

			return contact;
		}

		public void setContact(Integer contact) {

			this.contact = contact;
		}

		public String getQualification() {

			return qualification;
		}

		public void setQualification(String qualification) {

			this.qualification = qualification;
		}

		public String getSpecialization() {

			return specialization;
		}

		public void setSpecialization(String specialization) {

			this.specialization = specialization;
		}

		public Clinic getClinic() {

			return clinic;
		}

		@Override
		public String toString() {
			return "Doctor{" +
					"id=" + id +
					", name='" + name + '\'' +
					", email='" + email + '\'' +
					", doctor_id='" + doctorId + '\'' +
					", contact=" + contact +
					", qualification='" + qualification + '\'' +
					", specialization='" + specialization + '\'' +
					", clinic='" + clinic.getName() + '\'' +
					'}';
		}

	

		
}
