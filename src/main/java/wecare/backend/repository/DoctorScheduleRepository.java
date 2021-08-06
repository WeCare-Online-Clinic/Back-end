package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorScheduleRepository  extends JpaRepository<DoctorShedule,Integer> {

}
