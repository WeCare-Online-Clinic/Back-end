package wecare.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wecare.backend.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
