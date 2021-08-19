package wecare.backend.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.NurseSchedule;

@Repository
public interface NurseSchedulesRepository extends JpaRepository<NurseSchedule, Integer> {
	@Transactional
	@Modifying
	@Query("DELETE from NurseSchedule n WHERE n.nurse.id=:nurseId ")
	void deleteNurseScheduleById(@Param("nurseId") Integer nurseId);

}
