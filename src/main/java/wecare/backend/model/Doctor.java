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
		
		@Column(name="Address1")
		private String address1;
		
		@Column(name="Address2")
		private String address2;
		
		@Column(name="Mobile")
		private Integer mobile;
		
		@Column(name="Password")
		private String password;
		
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
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
		public Integer getMobile() {
			return mobile;
		}
		public void setMobile(Integer mobile) {
			this.mobile = mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
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
					+ ", nic=" + nic + ", address1=" + address1 + ", address2=" + address2 + ", mobile=" + mobile
					+ ", password=" + password + ", qualification=" + qualification + ", specialty=" + specialty
					+ ", clinic=" + clinic + "]";
		}
	
		
		
}
