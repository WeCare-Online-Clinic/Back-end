package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.ClinicMessage;

import java.util.List;

@Repository
public interface ClinicMessageRepository extends JpaRepository<ClinicMessage, Integer> {
    List<ClinicMessage> findAllByClinicIdOrderByDateAsc(Integer id);
}
