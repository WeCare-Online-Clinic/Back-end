package wecare.backend.controller;


import java.util.List;
import java.util.Optional;

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
import wecare.backend.model.Nurse;
import wecare.backend.service.NurseService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins="http://localhost:3000")
public class NurseController {

	@Autowired 
	private NurseService nurseService;
	
	@PostMapping("/addNurse")
	public ResponseEntity<Nurse> addNurse(@RequestBody Nurse nurse) throws UserCollectionException{
		return new ResponseEntity<Nurse>(nurseService.addNurse(nurse),HttpStatus.OK);
	}
	
	@GetMapping("/getNurses")
	public List<Nurse> getNurse(){
		List<Nurse> nurses=nurseService.getAllNurses();
		return nurses;
	}
	
	@GetMapping("/getNurseProfile/{id}")
	public Optional<Nurse> getNurseProfileById(@PathVariable Integer id){
		Optional<Nurse> nurse = nurseService.getNurseProfileById(id);
		return nurse;
		
	}
}
