package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.ConsultedPatientsCount;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.service.NurseDashboardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class NurseDashboardController {

    @Autowired
    private NurseDashboardService nurseDashboardService;

    @GetMapping("/getNurseDataCardDetails/{nurseId}")
    public List<DoctorDataCard> getNurseCardDetails(@PathVariable Integer nurseId) {
        List<DoctorDataCard> cardDetails = nurseDashboardService.getNurseCardDetails(nurseId);
        return cardDetails;
    }
    @GetMapping("/getConsultedPatientsDataNurse/{nurseId}")
    public ArrayList<ConsultedPatientsCount> getConsultedPatientsData(@PathVariable Integer nurseId){
        ArrayList<ConsultedPatientsCount> consultedPatients=nurseDashboardService.getConsultedPatientsData(nurseId);
        return  consultedPatients;
    }
}
