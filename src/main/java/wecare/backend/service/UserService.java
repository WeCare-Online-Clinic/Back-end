package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.User;
import wecare.backend.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired UserRepository userRepo;
	
	public User getSingleUser(User user) throws UserCollectionException{
		User resultUser = userRepo.findByEmail(user.getEmail());
		if(resultUser==null) {
			throw new UserCollectionException(UserCollectionException.NotFoundExeption(user.getEmail()));
		}
		else {
			if (user.getPassword().equals(resultUser.getPassword())) {
				return resultUser;
			}
			else{
				throw new UserCollectionException(UserCollectionException.UserPasswordWrong(user.getPassword()));
			}
		}
		
	}
}
