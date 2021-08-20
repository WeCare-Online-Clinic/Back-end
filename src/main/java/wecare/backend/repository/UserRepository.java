package wecare.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import wecare.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);


	
	@Query("SELECT COUNT(*) FROM User u WHERE u.role = 'patient' ")
	Integer  getPatientCount();
	
	@Query("SELECT COUNT(*) FROM User u WHERE u.role = 'doctor' ")
	Integer getDoctorCount();
	
	@Query("SELECT COUNT(*) FROM User u WHERE (u.role = 'nurse' OR u.role='headnurse') ")
	Integer getNurseCount();
	
	@Query("SELECT COUNT(*) FROM User u WHERE u.role = 'labtech' ")
	Integer getLabtechCount();
}
