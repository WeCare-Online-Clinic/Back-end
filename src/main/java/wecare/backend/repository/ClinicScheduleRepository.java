package wecare.backend.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.ClinicSchedule;

@Repository
public interface ClinicScheduleRepository extends JpaRepository<ClinicSchedule, Integer>{
	

//	@Query("SELECT * FROM clinic_shedule WHERE Clinic_Name=:clinic")
//	public List<ClinicSchedule> getClinicSheduleByName(@Param(value="clinic") String clinic);

    List<ClinicSchedule> findByClinicName(String clinicName);    
  
    @Query("FROM ClinicSchedule")
    public List<ClinicSchedule> getClinicShedule(@Param(value="doctor_id") Integer id);
    
    ClinicSchedule findByClinicIdAndDay(Integer id, String day);

     @Query("SELECT c.day from ClinicSchedule c WHERE c.clinic.id= :clinicId")
    List<String> getClinicShceduleByClinicId(@Param("clinicId") Integer clinicId);
}