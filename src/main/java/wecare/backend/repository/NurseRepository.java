package wecare.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer>{
	Nurse findByEmail(String email);
	
	List<Nurse> findByClinicId(Integer clinicId);

	@Query("SELECT n FROM Nurse n WHERE n.name LIKE %:name% AND d.status=true")
	List<Nurse> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT n FROM Nurse n WHERE n.id = :id AND d.status=true")
	List<Nurse> getNurseProfileById(@Param("id") Integer id);
	
}
