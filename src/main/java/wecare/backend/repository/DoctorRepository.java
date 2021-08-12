package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	Optional <Doctor> findById(Integer id);

	Doctor findByEmail(String email);
	
	List<Doctor> findByClinicId(Integer clinicId);

	@Query("SELECT d FROM Doctor d WHERE d.name LIKE %:name% ")
	List<Doctor> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT d FROM Doctor d WHERE d.id = :id")
	List<Doctor> getDoctorProfileById(@Param("id") Integer id);
	

	
	
}
