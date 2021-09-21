package wecare.backend.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientClinicData;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientClinicDataRepository extends JpaRepository<PatientClinicData, Integer> {

    // List<PatientClinicData> findAllByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(Integer id, Date date);
    PatientClinicData findFirstByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(Integer id, Date date);

    PatientClinicData findFirstByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDate(Integer id, Date date);

    List<PatientClinicData> findAllByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThan(Integer id, Date date);

    PatientClinicData findTopByClinicAppointment_PatientIdAndClinicAppointment_ClinicDateDateLessThanOrderByClinicAppointment_ClinicDateDateDesc(Integer id, Date date);
}
