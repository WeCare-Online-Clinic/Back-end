package wecare.backend.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.User;
import wecare.backend.repository.NurseRepository;
import wecare.backend.repository.UserRepository;

@Service
public class NurseService {
	
	@Autowired
	private NurseRepository nurseRepo;

	@Autowired
	private UserRepository userRepo;
	
	public Nurse addNurse(Nurse nurse) throws UserCollectionException{
		Nurse resultedNurse = nurseRepo.findByEmail(nurse.getEmail());
		Nurse newNurse = null;
		User newUser = new User();
		if(resultedNurse==null) {
			newNurse =  nurseRepo.saveAndFlush(nurse);
			newUser.setId(newNurse.getId());
			newUser.setUserRole("doctor");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
			newUser.setEmail(newNurse.getEmail());
			userRepo.save(newUser);
			return newNurse;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}

	
	public List<Nurse> getAllNurses(){
		List<Nurse> nurses = nurseRepo.findAll();
		return nurses;
	}
	
	public List<Nurse> getNurseProfileById(Integer id) {
		List<Nurse> nurse = nurseRepo.getNurseProfileById(id);
		return nurse;
	
	}
	
	public List<Nurse> getNurseProfileByName(String name){
		List<Nurse> nurse=nurseRepo.findByFirstNameLike(name);
		return nurse;
	}

	public List<Nurse> getNurseProfileByClinic(Integer clinicId){
		List<Nurse> nurse=nurseRepo.findByClinicId(clinicId);
		return nurse;
	}
}
