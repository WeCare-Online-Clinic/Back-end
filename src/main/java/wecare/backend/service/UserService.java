package wecare.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	public PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder(10);
	}
	
	public User getSingleUser(User user) throws UserCollectionException{
		User resultUser = userRepo.findByEmail(user.getEmail());

		if(resultUser==null) {
			throw new UserCollectionException(UserCollectionException.NotFoundExeption());
		}
		else {
			if (passwordEncoder().matches(user.getPassword(), resultUser.getPassword())) {
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
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepo.save(user);
	}

}
