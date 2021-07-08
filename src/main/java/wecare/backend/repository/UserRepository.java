package wecare.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wecare.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	User findByEmail(String email);

}
