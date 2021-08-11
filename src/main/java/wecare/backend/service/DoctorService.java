package wecare.backend.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.model.User;
import wecare.backend.repository.ClinicScheduleRepository;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.repository.DoctorSchedulesRepository;
import wecare.backend.repository.UserRepository;


@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;	
	
	@Autowired
	private DoctorSchedulesRepository doctorScheduleRepo;
	
	@Autowired
	private ClinicScheduleRepository clinicScheduleRepo;

	@Autowired
	private UserRepository userRepo;
	
	public Doctor addDoctor(Doctor doctor) throws UserCollectionException{
		Doctor resultDoctor=doctorRepo.findByEmail(doctor.getEmail());
		Doctor newDoctor = null;
		User newUser = new User();
		if(resultDoctor==null) {
			doctor.getDoctorSchedules();
			newDoctor = doctorRepo.saveAndFlush(doctor);
			newUser.setId(newDoctor.getId());
			newUser.setUserRole("doctor");
			newUser.setVerificationString("");
			newUser.setVerified(true);
			newUser.setPassword("");
			newUser.setEmail(newDoctor.getEmail());
			userRepo.save(newUser);
			return newDoctor;
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}


	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors =doctorRepo.findAll();
		return doctors;
	}

	public List<ClinicSchedule> getDoctorScheduleById(Integer id){
		List<ClinicSchedule> doctorSchedule = clinicScheduleRepo.getClinicShedule(id);
		return doctorSchedule;
	}
	
	public List<Doctor> getDoctorProfileById(Integer id) {
		List<Doctor> doctor = doctorRepo.getDoctorProfileById(id);
		return doctor;
	
	}
	
	public List<Doctor> getDocterProfileByName(String name){
		List<Doctor> doctor=doctorRepo.findByFirstNameLike(name);
		return doctor;
	}

	public List<Doctor> getDocterProfileByClinic(Integer clinicId){
		List<Doctor> doctor=doctorRepo.findByClinicId(clinicId);
		return doctor;
	}
	
	

}
