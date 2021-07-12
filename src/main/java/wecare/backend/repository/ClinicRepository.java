package wecare.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import wecare.backend.model.ClinicSchedule;

public interface ClinicRepository extends JpaRepository<ClinicSchedule, Integer>{

		
//	@Query("SELECT * FROM clinic_shedule WHERE Clinic_Name=:clinic")
//	public List<ClinicSchedule> getClinicSheduleByName(@Param(value="clinic") String clinic);
	
	List<ClinicSchedule> findByClinicName(String clinicName);
	
	

} 
