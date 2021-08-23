package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wecare.backend.model.PatientClinicProfile;

import java.util.List;

@Repository
public interface PatientClinicProfileRepository extends JpaRepository<PatientClinicProfile, Integer> {

    List<PatientClinicProfile> findByClinicId(Integer id);

}
