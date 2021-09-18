package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicAppointment;
import wecare.backend.model.Report;

import java.util.List;

@Repository
public interface PatientLabReports extends JpaRepository<Report, Integer> {

    @Query("SELECT c FROM Report c WHERE c.patient.id = :patientId AND c.availability=true")
    List<Report> getLabReportDetails(@Param("patientId") Integer patientId);

}
