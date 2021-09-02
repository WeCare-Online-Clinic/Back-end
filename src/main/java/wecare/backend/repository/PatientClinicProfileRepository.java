package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import wecare.backend.model.Patient;
import wecare.backend.model.PatientClinicProfile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientClinicProfileRepository extends JpaRepository<PatientClinicProfile, Integer> {

    List<PatientClinicProfile> findByClinicId(Integer id);
    PatientClinicProfile findFirstByPatientIdAndClinicId(Integer id, Integer cid);

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.clinic.id = :clinicId ")
    Integer getPatientCountInClinic(@Param("clinicId") Integer clinicId);

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.clinic.id = :clinicId AND (p.admissionDate BETWEEN :weekBeforeToday AND :toDay)")
    Integer getPatientCountInLastweek(@Param("clinicId") Integer clinicId, @Param("toDay") LocalDate toDay, @Param("weekBeforeToday") LocalDate weekBeforeToday);

    //Count for  Deseases in Cardiology
    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.diagnosis='coronary heart disease'")
    Integer getCoronoryDiseasePatientCount();

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.diagnosis='peripheral heart disease'")
    Integer getPeripheralDiseasePatientCount();

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.diagnosis='rheumatic heart disease'")
    Integer getRheumaticDiseasePatientCount();

    //Count for  Deseases in Gastroenterology
    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.diagnosis='Peptic Ulcer Disease'")
    Integer getPepticUlcerDiseasePatientCount();

    @Query("SELECT COUNT(*) FROM PatientClinicProfile p WHERE p.diagnosis='Crohn’s Disease'")
    Integer getCrohnsDiseasePatientCount();

    @Query("SELECT p from PatientClinicProfile p WHERE p.clinic.id= :clinicId")
    List<PatientClinicProfile> getPatientsInClinic(@Param("clinicId") Integer clinicId);

    @Query("SELECT p.admissionDate from PatientClinicProfile p WHERE p.clinic.id= :clinicId")
    List<LocalDate> getPatientsRegDatesInClinic(@Param("clinicId") Integer clinicId);


}
