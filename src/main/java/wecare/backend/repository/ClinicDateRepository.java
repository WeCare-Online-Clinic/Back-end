package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicDateRepository extends JpaRepository<ClinicDate, Integer> {

    List <ClinicDate> findByClinicSchedule_ClinicId(Integer id);
    ClinicDate findFirstByClinicSchedule_ClinicIdAndDate(Integer id, Date date);

    @Query("SELECT c.currQueue FROM ClinicDate c WHERE c.id = :Id")
    Integer getCurrQueueNo(@Param("Id") Integer id);

    @Query("SELECT COUNT(*) FROM ClinicDate c WHERE (c.date= :utilDate AND c.clinicSchedule.clinic.id= :clinicId) ")
    Integer getPatientCountOfNextClnic(@Param("utilDate") Date utilDate,@Param("clinicId") Integer clinicId);

    @Query("SELECT c.visitedPatients FROM ClinicDate c WHERE (c.date= :utilDate AND c.clinicSchedule.clinic.id= :clinicId) ")
    Integer getConsultedPatients(@Param("clinicId") Integer clinicId,@Param("utilDate") Date utilDate);
}
