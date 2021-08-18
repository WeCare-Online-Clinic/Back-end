package wecare.backend.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.service.DoctorService;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value="wecare")
@CrossOrigin(origins="http://localhost:3000")
public class DoctorController {
	private static final Logger LOG = LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/addDoctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) throws UserCollectionException, MessagingException, UnsupportedEncodingException {
		return new ResponseEntity<>(doctorService.addDoctor(doctor),HttpStatus.OK);
	}
	
	@GetMapping("/getDoctors")
	public List<Doctor> getDoctors(){
		List<Doctor> doctors = doctorService.getAllDoctors();
		return doctors;
	}
	
	@GetMapping("/getDoctorSchedule/{id}")
	public List<ClinicSchedule> getDoctorSchedules(@PathVariable Integer id){
		List<ClinicSchedule> docterSchedule =doctorService.getDoctorScheduleById(id);
		return docterSchedule;
	}
	
	@GetMapping("/getDoctorProfile/{id}") 
	public  List<Doctor> getDocterProfileById(@PathVariable Integer id){
		List<Doctor> doctor = doctorService.getDoctorProfileById(id);
		return doctor;
		
	}
	@GetMapping("/getDoctorProfileByName/{name}")
	public List<Doctor> getDocterProfileByName(@PathVariable String name){
		List<Doctor> doctor=doctorService.getDoctorProfileByName(name);
		return doctor;
		
	}
	
	@GetMapping("/getDoctorProfileDetailsByClinic/{clinicId}")
	public List<Doctor> getDocterProfileByClinic(@PathVariable Integer clinicId){
		List<Doctor> doctor=doctorService.getDoctorProfileByClinic(clinicId);
		return doctor;
		
	}

	@GetMapping("/doctor/info/{id}")
	public Doctor getDoctorInfo(@PathVariable Integer id){
		return doctorService.getDoctor(id);
	}
	
	@DeleteMapping("/deleteDoctorSchedule/{doctorId}")
	public void deleteDoctorScheduleById(@PathVariable Integer doctorId) {
	    	LOG.info("START : doctor Id {}",doctorId);
			doctorService.deleteDoctorScheduleById(doctorId);
	}
	
	@PostMapping("/updateDoctorSchedule")
	public void updateDoctorSchedule(@RequestBody List<DoctorSchedule> doctorSchedulelist){
		LOG.info("START : doctor Schedule {}",doctorSchedulelist);
		doctorService.updateDoctorSchedule(doctorSchedulelist);
	}
	
 }
