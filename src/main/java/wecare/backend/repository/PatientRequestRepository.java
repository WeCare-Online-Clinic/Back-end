package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientRequest;

import java.util.Date;
import java.util.List;

@Repository
public interface  PatientRequestRepository extends JpaRepository<PatientRequest, Integer> {

    List<PatientRequest> findByClinicIdAndChangedAndClinicDateDateGreaterThan(Integer cid, boolean b, Date date);
}
