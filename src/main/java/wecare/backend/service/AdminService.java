package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wecare.backend.model.*;
import wecare.backend.model.dto.UserCount;
import wecare.backend.repository.*;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private NurseRepository nurseRepo;

	@Autowired
	private DoctorSchedulesRepository doctorScheduleRepo;

	@Autowired
	private NurseSchedulesRepository nurseSchedulesRepo;
	
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
		doctorObject.setName("Registered Doctors");
		doctorObject.setValue(doctorCount);
		
		UserCount nurseObject =new UserCount();
		nurseObject.setName("Registered Nurses");
		nurseObject.setValue(nurseCount);
		
		UserCount labTechObject =new UserCount();
		labTechObject.setName("Registered Lab Technicians");
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

	public List<Doctor> getDoctorProfileByDoctorId(String doctorId) {
		return doctorRepo.getDoctorProfileByDoctorId(doctorId);
	}

	public List<Nurse> getNurseProfileByNurseId(String nurseId) {
		return nurseRepo.getNurseProfileByNurseId(nurseId);
	}

	public void deleteDoctorScheduleById(Integer doctorId) {
		doctorScheduleRepo.deleteDoctorScheduleById(doctorId);
	}

	public Integer updateDoctorSchedule(List<DoctorSchedule> doctorScheduleList) {
		List<DoctorSchedule> doctorSchedules= doctorScheduleRepo.saveAllAndFlush(doctorScheduleList);
		if(doctorSchedules.isEmpty()){
			return 0;
		}
		else {
			return 1;
		}
	}

	public void deleteNurseScheduleById(Integer nurseId) {
		nurseSchedulesRepo.deleteNurseScheduleById(nurseId);

	}

	public Integer updateNurseSchedule(List<NurseSchedule> nurseSchedulelist) {
		List<NurseSchedule> nurseSchedule = nurseSchedulesRepo.saveAllAndFlush(nurseSchedulelist);
		if(nurseSchedule.isEmpty()){
			return 0;
		}
		else{
			return 1;
		}
	}

	public List<User> getOnlineUsers(){
		List<User> users=userRepo.getOnlineUsers();
		return  users;
	}

}
