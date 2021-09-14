package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicDate;
import wecare.backend.model.Patient;
import wecare.backend.model.PatientRequest;

import java.util.Date;
import java.util.List;

@Repository
public interface  PatientRequestRepository extends JpaRepository<PatientRequest, Integer> {

    List<PatientRequest> findByClinicIdAndChangedAndClinicDateDateGreaterThan(Integer cid, boolean b, Date date);

   @Query("SELECT r from PatientRequest r WHERE r.patient= :patient AND r.clinicDate= :clinicDate AND r.clinic= :clinic  ")
    PatientRequest findIsRequestIsExisting(@Param("patient") Patient patient,@Param("clinicDate") ClinicDate clinicDate,@Param("clinic") Clinic clinic);
}
