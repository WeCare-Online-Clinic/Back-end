package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wecare.backend.model.dto.ConsultedPatientsCount;
import wecare.backend.model.dto.DoctorDataCard;
import wecare.backend.service.DoctorDashboardService;

import java.util.ArrayList;
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
    @GetMapping("/getDiognosisDetails/{clinicId}")
    public ArrayList<Integer> getDiognosisData(@PathVariable Integer clinicId){
        ArrayList<Integer> diognosisData=doctorDashboardService.getDiognosisData(clinicId);
        return diognosisData;
    }
    @GetMapping("/getPatientAgeData/{clinicId}")
    public ArrayList<Integer> getPatientsAgeCount(@PathVariable Integer clinicId){
            ArrayList<Integer> patientAgeData=doctorDashboardService.getPatientsAgeCount(clinicId);
            return  patientAgeData;
    }
    @GetMapping("/getPatientsCountInClinic/{clinicId}")
    public ArrayList<Integer> getPatientsInClinic(@PathVariable Integer clinicId){
            ArrayList<Integer> patientsCountInClinic=doctorDashboardService.getPatientsInClinic(clinicId);
            return  patientsCountInClinic;
    }
    @GetMapping("/getConsultedPatientsData/{doctorId}")
    public ArrayList<ConsultedPatientsCount>  getConsultedPatientsData(@PathVariable Integer doctorId){
        ArrayList<ConsultedPatientsCount> consultedPatients=doctorDashboardService.getConsultedPatientsData(doctorId);
        return  consultedPatients;
    }


}
