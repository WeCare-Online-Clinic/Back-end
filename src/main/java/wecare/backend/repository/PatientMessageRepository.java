package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientMessage;

import java.util.List;

@Repository
public interface PatientMessageRepository extends JpaRepository<PatientMessage, Integer> {
    List<PatientMessage> findByPatientId(Integer id);
}
