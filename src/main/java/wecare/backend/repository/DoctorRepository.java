package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	Optional <Doctor> findById(Integer id);

	Doctor findByEmail(String email);
	
	List<Doctor> findByClinicId(Integer clinicId);

	@Query("SELECT d FROM Doctor d WHERE d.name LIKE %:name%  AND d.status=true")
	List<Doctor> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT d FROM Doctor d WHERE d.id = :id AND d.status=true")
	List<Doctor> getDoctorProfileById(@Param("id") Integer id);

	
	
}
