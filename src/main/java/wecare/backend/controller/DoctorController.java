package wecare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping(value="wecare")
@CrossOrigin(origins="http://localhost:3000")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/addDoctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) throws UserCollectionException{
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

	
}
