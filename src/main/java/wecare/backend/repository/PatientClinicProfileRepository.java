package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientClinicProfile;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientClinicProfileRepository extends JpaRepository<PatientClinicProfile, Integer> {

    List<PatientClinicProfile> findByClinicId(Integer id);
    PatientClinicProfile findFirstByPatientIdAndClinicId(Integer id, Integer cid);

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.clinic.id = :clinicId ")
    Integer getPatientCountInClinic(@Param("clinicId") Integer clinicId);

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.clinic.id = :clinicId AND (p.admissionDate BETWEEN :weekBeforeToday AND :toDay)")
    Integer getPatientCountInLastweek(@Param("clinicId") Integer clinicId, @Param("toDay") LocalDate toDay, @Param("weekBeforeToday") LocalDate weekBeforeToday);

}
