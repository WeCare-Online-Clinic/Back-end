package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.DoctorPatientStats;
import wecare.backend.model.dto.PatientStatTitleDTO;
import wecare.backend.service.PatientStatService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "*")
public class PatientStatController {

    @Autowired
    private PatientStatService patientStatService;

    @GetMapping("/getPatientStatistics/{patientId}")
    public List<PatientStatTitleDTO> getPatientStatistics(@PathVariable Integer patientId) {
        List<PatientStatTitleDTO> patientStatData = patientStatService.getPatientStatistics(patientId);
        return patientStatData;
    }

    @PostMapping("/getPatientClinicStatistics/")
    public List<PatientStatTitleDTO> getPatientClinicStats(@RequestBody DoctorPatientStats doctorPatientStats) {
        List<PatientStatTitleDTO> patientStatData = patientStatService.getPatientClinicStats(doctorPatientStats);
        return patientStatData;
    }
}
