package wecare.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wecare.backend.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Integer>{

		
//	@Query("SELECT * FROM clinic_shedule WHERE Clinic_Name=:clinic")
//	public List<ClinicSchedule> getClinicSheduleByName(@Param(value="clinic") String clinic);
	
	List<Clinic> findAll();
	@Query("FROM Clinic")
	public List<Clinic> getClinic();

    Clinic findByClinicSchedulesId(Integer id);
}