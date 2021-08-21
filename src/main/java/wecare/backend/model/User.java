package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="role")
	private String role;
	
	@Column(name="verification_string")
	private String verificationString; 

	@Column(name="verified")
	private Boolean verified;

	@Column(name="password")
	private String password; 

	@Column(name="email")
	private String email;

	@Column(name="registered_date")
	private Date registeredDate;

	@Column(name="status")
	private boolean status;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserRole() {
		return role;
	}

	public void setUserRole(String role) {
		this.role = role;
	}

	public String getVerificationString() {
		return verificationString;
	}

	public void setVerificationString(String verificationString) {
		this.verificationString = verificationString;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", role='" + role + '\'' +
				", verificationString='" + verificationString + '\'' +
				", verified=" + verified +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", registeredDate=" + registeredDate +
				", status=" + status +
				'}';
	}
}
