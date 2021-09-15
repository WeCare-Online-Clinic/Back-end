package wecare.backend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Patient;
import wecare.backend.model.Test;



@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

	Optional <Test> findById(Integer id);
	

//	@Query("SELECT t FROM Test t WHERE t.name LIKE %:name% ")
//	List<Test> findByFirstNameLike(@Param("name") String name);
	
	@Query("SELECT t FROM Test t WHERE t.id = :id")
	List<Test> getTestProfileById(@Param("id") Integer id);

	Test findTopByOrderByIdDesc();

	@Query("SELECT t FROM Test t WHERE t.clinic.id = :clinicId")
    ArrayList<Test> getTestListByClinicId(@Param("clinicId") Integer clinicId);
}
