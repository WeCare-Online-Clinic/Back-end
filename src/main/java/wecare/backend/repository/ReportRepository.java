package wecare.backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import wecare.backend.model.Nurse;
import wecare.backend.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	Report findByEmail(String email);
	Optional <Report> findById(Integer id);
	
	List<Report> findByClinicId(Integer clinicId);
	
	@Query("SELECT r FROM Report r WHERE r.name LIKE %:name% ")
	List<Report> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT r FROM Report r WHERE r.id = :id")
	List<Report> getReportProfileById(@Param("id") Integer id);

}
