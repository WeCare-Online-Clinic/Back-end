package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wecare.backend.model.DoctorSchedule;

public interface DoctorSchedulesRepository extends JpaRepository<DoctorSchedule, Integer> {
		
		
}
