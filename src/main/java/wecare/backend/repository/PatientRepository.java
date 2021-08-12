package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
