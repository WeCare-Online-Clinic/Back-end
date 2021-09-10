package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientRequest;

@Repository
public interface  PatientRequestRepository extends JpaRepository<PatientRequest, Integer> {

}
