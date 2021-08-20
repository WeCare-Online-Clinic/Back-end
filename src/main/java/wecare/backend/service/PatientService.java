package wecare.backend.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Patient;
import wecare.backend.model.User;
import wecare.backend.repository.PatientRepository;
import wecare.backend.repository.UserRepository;

@Service
public class PatientService {
	
	private static final String resultedPatient = null;

	@Autowired
	private  PatientRepository patientRepo;

	@Autowired
	private UserRepository userRepo;
	
	public Patient addPatient(Patient patient) throws UserCollectionException{
    Patient resultedPatient = patientRepo.findByEmail(patient.getEmail());
		Patient newPatient = null;
		User newUser = new User();
		if(resultedPatient==null) {
			newPatient =  patientRepo.saveAndFlush(patient);
			newUser.setId(newPatient.getId());
			newUser.setUserRole("patient");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
            newUser.setEmail(newPatient.getEmail());
			userRepo.save(newUser);
			return newPatient;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}
	
	public List<Patient> getAllPatient(){
		List<Patient> patient = patientRepo.findAll();
		return patient;
	}
	
	public List<Patient> getPatientProfileById(Integer id) {
		List<Patient> patient = patientRepo.getPatientProfileById(id);
		return patient;
	
	}
	
	public List<Patient> getPatientProfileByName(String name){
		List<Patient> patient=patientRepo.findByFirstNameLike(name);
		return patient;
	}


}
