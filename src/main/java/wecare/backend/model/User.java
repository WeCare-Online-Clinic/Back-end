package wecare.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	private String id;

	@Column(name="role")
	private String role;
	
	@Column(name="verification_string")
	private String verificationString; 

	@Column(name="verified")
	private String verified; 

	@Column(name="password")
	private String password; 

	@Column(name="email")
	private String email;
	
	public String getUserRole() {
		return role;
	}

	public void setUserRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword(String password){
		return password;
	}
}
