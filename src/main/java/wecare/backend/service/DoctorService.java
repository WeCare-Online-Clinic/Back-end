package wecare.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.repository.ClinicRepository;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.repository.DoctorSchedulesRepository;


@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;	
	
	@Autowired
	private DoctorSchedulesRepository doctorScheduleRepo;
	
	@Autowired
	private ClinicRepository clinicRepo;
	

	
	public Doctor addDoctor(Doctor doctor) throws UserCollectionException{
		Doctor resultDoctor=doctorRepo.findByEmail(doctor.getEmail());
		if(resultDoctor==null) {
			doctor.getDoctorSchedule();
			return doctorRepo.saveAndFlush(doctor);
		}
		else {
			throw new UserCollectionException(UserCollectionException.UserAlreadyExist());
		}
	}


	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors =doctorRepo.findAll();
		return doctors;
	}
	
	
	public Doctor getDoctorProfileById(Integer id) {
		Doctor doctor = doctorRepo.findById(id);
		return doctor;
	}
	
	public List<ClinicSchedule> getDoctorScheduleById(Integer id){
		List<ClinicSchedule> doctorSchedule = clinicRepo.getClinicShedule(id);
		return doctorSchedule;
	}

	

	
}
