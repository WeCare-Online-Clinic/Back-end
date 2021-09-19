package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.LabTechDataCard;
import wecare.backend.service.LabTechDashboardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class LabTechDashboardController {

    @Autowired
    private LabTechDashboardService labTechDashboardService;

    @GetMapping("/getLabTechDataCardDetails/")
    public List<LabTechDataCard> getLabTechDataCardDetails() {
        List<LabTechDataCard> cardDetails = labTechDashboardService.getLabTechCardDetails();
        return cardDetails;
    }

    @GetMapping("/getMonthlyIssuedReportsCount/")
    public ArrayList<Integer> getMonthlyIssuedReportsCount() {
        ArrayList<Integer> monthlyIssuedReportCount = labTechDashboardService.getMonthlyIssuedReportsCount();
        return monthlyIssuedReportCount;
    }

    @GetMapping("/getIssuedReportTypes/")
    public ArrayList<Integer> getIssuedReportTypes() {
        ArrayList<Integer> IssuedReportTypesCount = labTechDashboardService.getIssuedReportTypes();
        return IssuedReportTypesCount;
    }

}
