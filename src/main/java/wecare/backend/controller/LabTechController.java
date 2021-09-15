package wecare.backend.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.*;
import wecare.backend.model.dto.PatientForLabTech;
import wecare.backend.service.LabTechService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class LabTechController {

    @Autowired
    private LabTechService labTechService;

    @GetMapping("/labTech/info/{id}")
    public LabTechnician getLabTechInfo(@PathVariable Integer id) {
        return labTechService.getLabTechInfo(id);
    }
    
	@GetMapping("/getTest")
	public List<Test> getTest() {
		List<Test> test = labTechService.getAllTest();
		return test;
	}

	@GetMapping("/getTestProfile/{id}")
	public List<Test> getTestProfileById(@PathVariable Integer id) {
		List<Test> test = labTechService.getTestProfileById(id);
		return test;

	}
	
	@GetMapping("/getReport")
	public List<Report> getReport() {
		List<Report> report = labTechService.getAllReport();
		return report;
	}

	@GetMapping("/getReportProfile/{id}")
	public List<Report> getReportProfileById(@PathVariable Integer id) {
		List<Report> report = labTechService.getReportProfileById(id);
		return report;

	}

	@GetMapping("/getPatientProfileByNIC/{patientNIC}")
	public PatientForLabTech getPatientByNIC(@PathVariable String patientNIC){
		PatientForLabTech patient=labTechService.getPatientByNIC(patientNIC);
    	return patient;
	}

	@PostMapping("/getTestTypes/")
	public ArrayList<Test> getTestTypes(@RequestBody PatientForLabTech patientProfile){
		ArrayList<Test> tests=labTechService.getTestTypes(patientProfile);
		return  tests;
	}
    
}
