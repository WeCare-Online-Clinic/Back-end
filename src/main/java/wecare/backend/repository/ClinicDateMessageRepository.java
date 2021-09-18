package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicDateMessage;

import java.util.List;

@Repository
public interface ClinicDateMessageRepository extends JpaRepository<ClinicDateMessage, Integer> {
    List<ClinicDateMessage> findAllByClinicDate_ClinicSchedule_ClinicIdOrderByDateAsc(Integer id);

    List<ClinicDateMessage> findAllByClinicDateIdOrderByDateAsc(Integer id);
}
