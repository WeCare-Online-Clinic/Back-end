package wecare.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Doctor;
import wecare.backend.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;
	

	
	public Doctor addDoctor(Doctor doctor) throws UserCollectionException{
		Doctor resultDoctor=doctorRepo.findByEmail(doctor.getEmail());
		if(resultDoctor==null) {
			return doctorRepo.save(doctor);
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}


	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors =doctorRepo.findAll();
		return doctors;
	}

	
	
	
}
