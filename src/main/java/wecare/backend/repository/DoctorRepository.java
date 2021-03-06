package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.PatientClinicData;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	Optional <Doctor> findById(Integer id);

	Doctor findByEmail(String email);

	@Query("SELECT d FROM Doctor d WHERE  d.status=true")
	List<Doctor> findAllDoctors();

	@Query("SELECT d FROM Doctor d WHERE d.clinic.id= :clinicId AND d.status=true")
	List<Doctor> findByClinicId(@Param("clinicId") Integer clinicId);

	@Query("SELECT d FROM Doctor d WHERE d.name LIKE %:name%  AND d.status=true")
	List<Doctor> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT d FROM Doctor d WHERE d.id = :id AND d.status=true")
	List<Doctor> getDoctorProfileById(@Param("id") Integer id);

	@Query("SELECT d FROM Doctor d WHERE d.doctorId = :doctorId AND d.status=true")
	List<Doctor> getDoctorProfileByDoctorId(@Param("doctorId") String doctorId);
}
