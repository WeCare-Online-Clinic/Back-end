package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.springframework.stereotype.Component;

@Entity
@Table(name="doctor")
public class Doctor {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(name="native",strategy = "native")
		@Column(name="ID")
		private Integer id;	
		
	
		@Column(name="First_Name")
		private String firstName;
		
		@Column(name="Last_Name")
		private String lastName;
		
		@Column(name="Email")
		private String email;
		
		@Column(name="NIC")
		private String nic;	
		
		@Column(name="Mobile")
		private Integer mobile;		
		
		@Column(name="Qualification")
		private String qualification;
		
		@Column(name="Specialty")
		private String specialty;
		
		@Column(name="Clinic_ID")
		private String clinic;
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNic() {
			return nic;
		}
		public void setNic(String nic) {
			this.nic = nic;
		}
		public Integer getMobile() {
			return mobile;
		}
		public void setMobile(Integer mobile) {
			this.mobile = mobile;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getSpecialty() {
			return specialty;
		}
		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}
		public String getClinic() {
			return clinic;
		}
		public void setClinic(String clinic) {
			this.clinic = clinic;
		}
	
		@Override
		public String toString() {
			return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", nic=" + nic + ", mobile=" + mobile + ", qualification=" + qualification + ", specialty="
					+ specialty + ", clinic=" + clinic + "]";
		}
		
		
}
