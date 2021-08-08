package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	Optional<Doctor> findById(Integer id);
	Doctor findByEmail(String email);

//		@Query("SELECT s FROM Student s WHERE name=:name")
//		public Student getStudentByName(@Param(value="name") String name);

}
