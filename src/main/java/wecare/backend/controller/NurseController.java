package wecare.backend.controller;

import java.util.List;

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
import wecare.backend.model.Nurse;
import wecare.backend.model.NurseSchedule;
import wecare.backend.service.NurseService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class NurseController {

	@Autowired
	private NurseService nurseService;

	@PostMapping("/addNurse")
	public ResponseEntity<Nurse> addNurse(@RequestBody Nurse nurse) throws UserCollectionException {
		return new ResponseEntity<Nurse>(nurseService.addNurse(nurse), HttpStatus.OK);
	}

	@GetMapping("/getNurses")
	public List<Nurse> getNurse() {
		List<Nurse> nurses = nurseService.getAllNurses();
		return nurses;
	}

	@GetMapping("/getNurseProfile/{id}")
	public List<Nurse> getNurseProfileById(@PathVariable Integer id) {
		List<Nurse> nurse = nurseService.getNurseProfileById(id);
		return nurse;

	}

	@GetMapping("/getNurseProfileByName/{name}")
	public List<Nurse> getNurseProfileByName(@PathVariable String name) {
		List<Nurse> nurse = nurseService.getNurseProfileByName(name);
		return nurse;

	}

	@GetMapping("/getNurseProfileDetailsByClinic/{clinicId}")
	public List<Nurse> getNurseProfileByClinic(@PathVariable Integer clinicId) {
		List<Nurse> nurse = nurseService.getNurseProfileByClinic(clinicId);
		return nurse;

	}

	@DeleteMapping("/deleteNurseSchedule/{nurseId}")
	public void deleteNurseScheduleById(@PathVariable Integer nurseId) {
		nurseService.deleteNurseScheduleById(nurseId);
	}

	@PostMapping("/updateNurseSchedule")
	public List<NurseSchedule> updateNurseSchedule(@RequestBody List<NurseSchedule> nurseSchedulelist) {
		List<NurseSchedule> nurseSchedule = nurseService.updateNurseSchedule(nurseSchedulelist);
		return nurseSchedule;
	}
}
