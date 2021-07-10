package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
		Doctor findByEmail(String email);
}
