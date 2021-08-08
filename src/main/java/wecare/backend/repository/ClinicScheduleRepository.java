package wecare.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wecare.backend.model.ClinicSchedule;
import wecare.backend.model.Clinic;

public interface ClinicScheduleRepository extends JpaRepository<ClinicSchedule, Integer>{


//	@Query("SELECT * FROM clinic_shedule WHERE Clinic_Name=:clinic")
//	public List<ClinicSchedule> getClinicSheduleByName(@Param(value="clinic") String clinic);

    List<ClinicSchedule> findByClinicName(String clinicName);

    @Query("FROM ClinicSchedule")
    public List<ClinicSchedule> getClinicShedule(@Param(value="doctor_id") Integer id);

}