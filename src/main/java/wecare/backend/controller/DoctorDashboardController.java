package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.service.DoctorDashboardService;

import java.util.List;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorDashboardController {

    @Autowired
    private DoctorDashboardService doctorDashboardService;

    @GetMapping("/getDoctorDataCardDetails/{doctorId}")
    public List<DoctorDataCard> getDoctorCardDetails(@PathVariable Integer doctorId) {
        List<DoctorDataCard> cardDetails = doctorDashboardService.getDoctorCardDetails(doctorId);
        return cardDetails;
    }

}
