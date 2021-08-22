package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.User;
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

	public Integer changeDoctorStatus(Integer doctorId){
		Optional<Doctor> doctorOptional=doctorRepo.findById(doctorId);
		if(doctorOptional.isPresent()){
			Doctor s = doctorOptional.get();
			s.setStatus(false);
			doctorRepo.saveAndFlush(s);

			Optional<User> userOptional=userRepo.findById(doctorId);
			if(userOptional.isPresent()){
				User u=userOptional.get();
				u.setStatus(false);
				userRepo.saveAndFlush(u);
			}
			return 1;
		}else {
			return 0;
		}
	}
	public Integer changeNurseStatus(Integer nurseId){
		Optional<Nurse> nurseOptional=nurseRepo.findById(nurseId);
		if(nurseOptional.isPresent()){
			Nurse n= nurseOptional.get();
			n.setStatus(false);
			nurseRepo.saveAndFlush(n);

			Optional<User> userOptional=userRepo.findById(nurseId);
			if(userOptional.isPresent()){
				User u=userOptional.get();
				u.setStatus(false);
				userRepo.saveAndFlush(u);
			}
			return 1;
		}else {
			return 0;
		}
	}

}
