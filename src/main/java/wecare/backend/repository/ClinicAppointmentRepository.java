package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicAppointment;

import java.util.List;

@Repository
public interface ClinicAppointmentRepository extends JpaRepository<ClinicAppointment, Integer> {

    List <ClinicAppointment> findByClinicDateId(Integer clinic_did);

    ClinicAppointment findFirstByPatientIdAndClinicDateId(Integer pid, Integer did);

    List<ClinicAppointment> findByClinicDateIdAndVisited(Integer id, boolean b);
}
