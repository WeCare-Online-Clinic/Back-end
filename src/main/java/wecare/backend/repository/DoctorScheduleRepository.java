package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository  extends JpaRepository<DoctorSchedule,Integer> {

}
