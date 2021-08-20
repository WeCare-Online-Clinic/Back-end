package wecare.backend.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
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
import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
import wecare.backend.model.Patient;
import wecare.backend.service.PatientService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins="http://localhost:3000")
public class PatientController {

	@Autowired 
	private PatientService patientService;
	
	@PostMapping("/addPatient")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) throws UserCollectionException, MessagingException, UnsupportedEncodingException {
		return new ResponseEntity<Patient>(patientService.addPatient(patient),HttpStatus.OK);
	}
	
	@GetMapping("/getPatient")
	public List<Patient> getPatient(){
		List<Patient> patient=patientService.getAllPatient();
		return patient;
	}
	
	
	@GetMapping("/getPatientProfile/{id}") 
	public  List<Patient> getPatientProfileById(@PathVariable Integer id){
		List<Patient> patient = patientService.getPatientProfileById(id);
		return patient;
		
	}
	@GetMapping("/getPatientProfileByName/{name}")
	public List<Patient> getPatientProfileByName(@PathVariable String name){
		List<Patient> patient=patientService.getPatientProfileByName(name);
		return patient;
		
	}
	
}
