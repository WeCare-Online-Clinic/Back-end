package wecare.backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import wecare.backend.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByEmail(String email);
	Optional <Patient> findById(Integer id);
	
	@Query("SELECT n FROM Patient n WHERE n.name LIKE %:name% ")
	List<Patient> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT n FROM Patient n WHERE n.id = :id")
	List<Patient> getPatientProfileById(@Param("id") Integer id);

}
