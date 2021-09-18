
package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import wecare.backend.model.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	
	@Query("SELECT r FROM Report r WHERE r.id = :id")
	Report getReportProfileById(@Param("id") Integer id);
	


	Report findTopByOrderByIdDesc();
}
