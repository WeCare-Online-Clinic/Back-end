package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicDateRepository extends JpaRepository<ClinicDate, Integer> {

    List <ClinicDate> findByClinicSchedule_ClinicId(Integer id);
    ClinicDate findFirstByClinicSchedule_ClinicIdAndDate(Integer id, Date date);
}
