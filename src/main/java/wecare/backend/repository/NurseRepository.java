package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer>{
	Nurse findByEmail(String email);
}
