package wecare.backend.controller;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wecare.backend.model.*;
import wecare.backend.model.dto.PatientForLabTech;
import wecare.backend.model.dto.PatientReportDTO;
import wecare.backend.model.dto.PatientTest;
import wecare.backend.service.LabTechService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class LabTechController {

    @Autowired
    private LabTechService labTechService;

    @GetMapping("/labTech/info/{id}")
    public LabTechnician getLabTechInfo(@PathVariable Integer id) {
        return labTechService.getLabTechInfo(id);   }
	

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
    public Report getReportProfileById(@PathVariable Integer id) {
        Report report = labTechService.getReportProfileById(id);
        return report;

    }

    @GetMapping("/getPatientProfileByNIC/{patientNIC}")
    public PatientForLabTech getPatientByNIC(@PathVariable String patientNIC) {
        PatientForLabTech patient = labTechService.getPatientByNIC(patientNIC);
        return patient;
    }

    @PostMapping("/getTestTypes/")
    public ArrayList<Test> getTestTypes(@RequestBody PatientForLabTech patientProfile) {
        ArrayList<Test> tests = labTechService.getTestTypes(patientProfile);
        return tests;
    }

    @PostMapping("/savePatientTest/")
    public String savePatientTest(@RequestBody PatientTest patientTest) {
        Integer result = labTechService.savePatientTest(patientTest);
        if (result == 1) {
            return "successfully added the test ";
        } else {
            return "failed to add the test!! please try again";
        }

    }

    @PostMapping("/addReport/")
    public String uploadLeadDocument(@RequestParam("uploadingFile") MultipartFile uploadFiles, @RequestParam("uploadRequestData") String uploadRQData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PatientReportDTO patientReport = objectMapper.readValue(uploadRQData, PatientReportDTO.class);

        patientReport.setPdfReport(uploadFiles.getBytes());

        Integer result = labTechService.saveReport(patientReport);
        if (result == 1) {
            return "successfully added the report ";
        } else {
            return "failed to add the report!! please try again";
        }


    }

    //lab Report Search
    @GetMapping("/getPatientReportsByPatientName/{patientName}")
    public  List<Report> getPatientReportsByPatientName(@PathVariable String patientName){
        List<Report> reports = labTechService.getPatientReportsByPatientName(patientName);
        return reports;

    }
    @GetMapping("/getPatientReportsByPatientNIC/{patientNIC}")
    public List<Report> getPatientReportsByPatientNIC(@PathVariable String patientNIC){
        List<Report> reports=labTechService.getPatientReportsByPatientNIC(patientNIC);
        return reports;

    }

    @GetMapping("/getPatientReportsByTestType/{testType}")
    public List<Report> getPatientReportsByTestType(@PathVariable Integer testType){
        List<Report> reports=labTechService.getPatientReportsByTestType(testType);
        return reports;

    }


}
