package wecare.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wecare.backend.model.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> {

    Nurse findByEmail(String email);

    @Query("SELECT n FROM Nurse n WHERE  n.status=true")
    List<Nurse> findAllNurses();

    @Query("SELECT n FROM Nurse n WHERE n.clinic.id= :clinicId AND n.status=true")
    List<Nurse> findByClinicId(@Param("clinicId") Integer clinicId);

    @Query("SELECT n FROM Nurse n WHERE n.name LIKE %:name% AND n.status=true")
    List<Nurse> findByFirstNameLike(@Param("name") String name);

    @Query("SELECT n FROM Nurse n WHERE n.id = :id AND n.status=true")
    List<Nurse> getNurseProfileById(@Param("id") Integer id);

    @Query("SELECT n FROM Nurse n WHERE n.nurseId = :nurseId AND n.status=true")
    List<Nurse> getNurseProfileByNurseId(@Param("nurseId") String nurseId);

}
