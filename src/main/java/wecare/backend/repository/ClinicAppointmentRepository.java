package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicAppointment;

import java.util.List;

@Repository
public interface ClinicAppointmentRepository extends JpaRepository<ClinicAppointment, Integer> {

    List <ClinicAppointment> findByClinicDateId(Integer clinic_did);

    ClinicAppointment findFirstByPatientIdAndClinicDateId(Integer pid, Integer did);

    List<ClinicAppointment> findByClinicDateIdAndVisited(Integer id, boolean b);

    @Query("SELECT c FROM ClinicAppointment c WHERE c.patient.id = :patientId AND c.clinicDate.date>CURRENT_DATE ")
    List<ClinicAppointment> getNextClinicDetails(@Param("patientId") Integer patientId);

    void deleteByPatientIdAndClinicDateId(Integer id, Integer id1);
}
