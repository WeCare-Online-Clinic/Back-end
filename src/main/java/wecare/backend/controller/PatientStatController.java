package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.model.dto.PatientStatTitleDTO;
import wecare.backend.service.PatientStatService;

import java.util.List;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientStatController {

    @Autowired
    private PatientStatService patientStatService;

    @GetMapping("/getPatientStatistics/{patientId}")
    public List<PatientStatTitleDTO> getPatientStatistics(@PathVariable Integer patientId) {
        List<PatientStatTitleDTO> patientStatData = patientStatService.getPatientStatistics(patientId);
        return patientStatData;
    }
}
