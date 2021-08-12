package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.User;
import wecare.backend.repository.*;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	DoctorRepository doctorRepo;

	@Autowired
	NurseRepository nurseRepo;

	@Autowired
	PatientRepository patientRepo;

	@Autowired
	LabTechnicianRepository labTechnicianRepo;

	@Autowired
	AdminRepository adminRepository;
	
	public User getSingleUser(User user) throws UserCollectionException{
		User resultUser = userRepo.findByEmail(user.getEmail());
		if(resultUser==null) {
			throw new UserCollectionException(UserCollectionException.NotFoundExeption());
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

	public User getUser(Integer id) throws UserCollectionException {
		User resultUser = userRepo.findById(id).get();
		if(resultUser == null){
			throw new UserCollectionException(UserCollectionException.NotFoundExeption());
		}
		else{
			return resultUser;
		}
	}

	public User setPassword(User user){
		return userRepo.save(user);
	}

}
