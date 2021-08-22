package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.dto.UserCount;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.repository.NurseRepository;
import wecare.backend.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private NurseRepository nurseRepo;
	
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

	public Integer changeDoctorStatus(Doctor doctor){
		Boolean status=doctor.getStatus();
		if(status==true){
			doctor.setStatus(false);
			doctorRepo.save(doctor);
			return 1;//update succesfull
		}
		return 0;

	}
	public Integer changeNurseStatus(Nurse nurse){
		Boolean status=nurse.getStatus();
		if(status==true){
			nurse.setStatus(false);
			nurseRepo.save(nurse);
			return 1;//update succesfull
		}
		return 0;

	}

}
