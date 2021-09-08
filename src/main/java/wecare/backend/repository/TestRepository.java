package wecare.backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import wecare.backend.model.Nurse;
import wecare.backend.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
	Test findByEmail(String email);
	Optional <Test> findById(Integer id);
	
	List<Test> findByClinicId(Integer clinicId);
	
	@Query("SELECT t FROM Test t WHERE t.name LIKE %:name% ")
	List<Test> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT t FROM Test t WHERE t.id = :id")
	List<Test> getTestProfileById(@Param("id") Integer id);

}
