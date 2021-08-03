package wecare.backend.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Nurse;
import wecare.backend.repository.NurseRepository;

@Service
public class NurseService {
	
	@Autowired
	private NurseRepository nurseRepo;
	
	public Nurse addNurse(Nurse nurse) throws UserCollectionException{
		Nurse resultedNurse = nurseRepo.findByEmail(nurse.getEmail());
		if(resultedNurse==null) {
			return nurseRepo.saveAndFlush(nurse);
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}

	
	public List<Nurse> getAllNurses(){
		List<Nurse> nurses = nurseRepo.findAll();
		return nurses;
	}
	
	public Optional<Nurse> getNurseProfileById(Integer id) {
		Optional<Nurse> nurse = nurseRepo.findById(id);
		return nurse;
	}
}
