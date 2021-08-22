package wecare.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.LabTechnician;
import wecare.backend.model.Patient;

@Repository
public interface LabTechnicianRepository extends JpaRepository<LabTechnician, Integer> {
	LabTechnician findByEmail(String email);
	Optional <LabTechnician> findById(Integer id);
	
	@Query("SELECT n FROM LabTechnician n WHERE n.name LIKE %:name% ")
	List<LabTechnician> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT n FROM LabTechnician n WHERE n.id = :id")
	List<LabTechnician> getLabTechnicianProfileById(@Param("id") Integer id);
}
