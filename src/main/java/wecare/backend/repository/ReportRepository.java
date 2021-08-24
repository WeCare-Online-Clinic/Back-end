package wecare.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    Report findByEmail(String email);

    @Query("SELECT n FROM Report n WHERE  n.status=true")
    List<Report> findAllReport();

    @Query("SELECT n FROM Report n WHERE n.name LIKE %:name% AND n.status=true")
    List<Report> findByFirstNameLike(@Param("name") String name);

    @Query("SELECT n FROM Report n WHERE n.id = :id AND n.status=true")
    List<Report> getReportProfileById(@Param("id") Integer id);

}
