package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import wecare.backend.model.dto.UserCount;
import wecare.backend.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<UserCount> getUserCounts(){
		
		List<UserCount> userCount= new ArrayList<>();
		
		Integer patientCount=userRepo.getPatientCount();
		Integer doctorCount=userRepo.getDoctorCount();
		Integer nurseCount=userRepo.getNurseCount();
		Integer labtechCount=userRepo.getLabtechCount();
		
		UserCount patientObject =new UserCount();
		patientObject.setName("Registered Patients");
		patientObject.setValue(patientCount);
		
		UserCount doctorObject =new UserCount();
		doctorObject.setName("Doctors");
		doctorObject.setValue(doctorCount);
		
		UserCount nurseObject =new UserCount();
		nurseObject.setName("Nurses");
		nurseObject.setValue(nurseCount);
		
		UserCount labTechObject =new UserCount();
		labTechObject.setName("Lab Technicians");
		labTechObject.setValue(labtechCount);
		
		userCount.add(patientObject);
		userCount.add(doctorObject);
		userCount.add(nurseObject);
		userCount.add(labTechObject);
		
		return userCount;
	}

}
