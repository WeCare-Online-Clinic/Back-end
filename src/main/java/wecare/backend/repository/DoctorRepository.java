package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import wecare.backend.model.Doctor;
import wecare.backend.model.ClinicSchedule;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
		Doctor findByEmail(String email);
		Doctor findById(Integer id);
		
//		@Query("SELECT s FROM Student s WHERE name=:name")
//		public Student getStudentByName(@Param(value="name") String name);

}
