package wecare.backend.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.NurseSchedule;
import wecare.backend.model.User;
import wecare.backend.repository.NurseRepository;
import wecare.backend.repository.NurseSchedulesRepository;
import wecare.backend.repository.UserRepository;

@Service
public class NurseService {
	
	@Autowired
	private NurseRepository nurseRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NurseSchedulesRepository nurseSchedulesRepo;
	
	public Nurse addNurse(Nurse nurse) throws UserCollectionException{
		User resultedNurse = userRepo.findByEmail(nurse.getEmail());
		Nurse newNurse = null;
		User newUser = new User();
		if(resultedNurse==null) {
			nurse.setRegisteredDate(new Date());
			newNurse =  nurseRepo.saveAndFlush(nurse);
			newUser.setId(newNurse.getId());
			newUser.setName(nurse.getName());
			newUser.setUserRole(newNurse.getIsHead().equals(true)?"headnurse":"nurse");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
			newUser.setEmail(newNurse.getEmail());
			newUser.setStatus(true);
			newUser.setLoginStatus(false);
			newUser.setRegisteredDate(new Date());
			userRepo.save(newUser);
			return newNurse;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}

	
	public List<Nurse> getAllNurses(){
		List<Nurse> nurses = nurseRepo.findAllNurses();
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

	public Nurse getNurse(Integer id){
		return nurseRepo.findById(id).get();
	}

	

}
