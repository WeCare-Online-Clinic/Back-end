package wecare.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;

public interface DoctorSchedulesRepository extends JpaRepository<DoctorSchedule, Integer> {
		
	
	@Transactional
	 @Modifying
    @Query("DELETE from DoctorSchedule d WHERE d.doctor.id=:doctorId ")
	void deleteDoctorScheduleById(@Param("doctorId") Integer doctorId);
}
