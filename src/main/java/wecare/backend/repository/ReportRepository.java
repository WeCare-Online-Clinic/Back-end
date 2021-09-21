
package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import wecare.backend.model.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	
	@Query("SELECT r FROM Report r WHERE r.id = :id")
	Report getReportProfileById(@Param("id") Integer id);

	Report findTopByOrderByIdDesc();

	List<Report> findAllByOrderByIdDesc();

	@Query("SELECT COUNT(*) FROM Report r WHERE r.testDate= :toDay")
	Integer getTestCountAddedToday(@Param("toDay") LocalDate toDay);

	@Query("SELECT COUNT(*) FROM Report r WHERE (r.issuedDate= :toDay AND r.availability=true)")
	Integer getReportCountIssuedToday(@Param("toDay") LocalDate toDay);

	@Query("SELECT COUNT(*) FROM Report r WHERE ( YEAR(r.issuedDate) = :year AND MONTH(r.issuedDate)= :month) ")
	Integer getReportCountIssuedThisMonth(@Param("year") int year,@Param("month") int month);

	@Query("SELECT COUNT(*) FROM Report r WHERE r.availability=false")
	Integer getReportCountToBeIssued();

	@Query("SELECT COUNT(*) FROM Report r WHERE ( YEAR(r.issuedDate) = :year AND MONTH(r.issuedDate)= :month)")
	Integer getMonthlyIssuedCount(@Param("year") int year, @Param("month") int month);

	@Query("SELECT COUNT(*) FROM Report r WHERE r.test.id= :type")
	Integer getIssuedReportTypeCount(@Param("type") int type);

    List<Report> findByPatientIdAndTestClinicId(Integer pid, Integer cid);

    Report findTopByPatientIdAndTestClinicIdOrderByIssuedDate(Integer pid, Integer cid);
}
