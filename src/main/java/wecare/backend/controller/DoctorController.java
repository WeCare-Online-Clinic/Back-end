package wecare.backend.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
import wecare.backend.model.*;
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
		List<ClinicSchedule> doctorSchedule =doctorService.getDoctorScheduleById(id);
		return doctorSchedule;
	}
	
	@GetMapping("/getDoctorProfile/{id}") 
	public  List<Doctor> getDoctorProfileById(@PathVariable Integer id){
		List<Doctor> doctor = doctorService.getDoctorProfileById(id);
		return doctor;
		
	}
	@GetMapping("/getDoctorProfileByName/{name}")
	public List<Doctor> getDoctorProfileByName(@PathVariable String name){
		List<Doctor> doctor=doctorService.getDoctorProfileByName(name);
		return doctor;
		
	}
	
	@GetMapping("/getDoctorProfileDetailsByClinic/{clinicId}")
	public List<Doctor> getDoctorProfileByClinic(@PathVariable Integer clinicId){
		List<Doctor> doctor=doctorService.getDoctorProfileByClinic(clinicId);
		return doctor;
		
	}

	@GetMapping("/doctor/info/{id}")
	public Doctor getDoctorInfo(@PathVariable Integer id){
		return doctorService.getDoctor(id);
	}
	


	@GetMapping("/patient/list/{id}")
	public List<PatientClinicProfile> getPatients(@PathVariable Integer id){
		return doctorService.getPatientList(id);
	}

	@GetMapping("/clinic/history/{id}")
	public List <ClinicDate> getClinicDates(@PathVariable Integer id){
		return doctorService.getClinicDates(id);
	}

	@GetMapping("/clinic/queue/{id}")
	public List <ClinicAppointment> getQueue(@PathVariable Integer id){
		return doctorService.getQueue(id);
	}

	@GetMapping("/patient/clinic/data/{id}")
	public PatientClinicData getPatientClinicData(@PathVariable Integer id){
		return doctorService.getPatientClinicData(id);
	}

	@GetMapping("/patient/clinic/data/list/{id}")
	public List<PatientClinicData> getPatientClinicDataList(@PathVariable Integer id){
		return doctorService.getPatientClinicDataList(id);
	}

	@GetMapping("/consultation/available/{id}")
	public ClinicDate getClinicDate(@PathVariable Integer id) throws ParseException {
		return doctorService.getClinicDate(id);
	}
 }
