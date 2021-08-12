package wecare.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.LabTechnician;

@Repository
public interface LabTechnicianRepository extends JpaRepository<LabTechnician, Integer> {
}
