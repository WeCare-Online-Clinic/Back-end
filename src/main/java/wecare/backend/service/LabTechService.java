package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.*;
import wecare.backend.model.dto.PatientForLabTech;
import wecare.backend.repository.*;


@Service
public class LabTechService {
    @Autowired
    private LabTechnicianRepository labTechnicianRepo;
    @Autowired
    private TestRepository testRepo;
    @Autowired
    private ReportRepository reportRepo;
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private PatientClinicProfileRepository patientClinicProfileRepo;


    public LabTechnician getLabTechInfo(Integer id){
        return labTechnicianRepo.findById(id).get();
    }
    public List<Report> getAllReport(){
		List<Report> report = reportRepo.findAll();
		return report;
	}
	
	public List<Report> getReportProfileById(Integer id) {
		List<Report> report = reportRepo.getReportProfileById(id);
		return report;
	
	}

	  public List<Test> getAllTest(){
		  List<Test> test=testRepo.findAll();
		  return test;
	  }	  

	
	public List<Test> getTestProfileById(Integer id){
		List<Test> test = testRepo.getTestProfileById(id);
		return test;
	
	}

	public PatientForLabTech getPatientByNIC(String patientNIC) {
		Patient patient=patientRepo.findByNic(patientNIC);
		PatientForLabTech patientForLabTech = new PatientForLabTech();
		List<PatientClinicProfile> patientClinicProfile= patientClinicProfileRepo.getPatientByNIC(patientNIC);
		patientForLabTech.setContact(patient.getContact());
		patientForLabTech.setEmail(patient.getEmail());
		patientForLabTech.setName(patient.getName());
		patientForLabTech.setNic(patient.getNIC());
		patientForLabTech.setGender(patient.getGender());

		ArrayList<Integer> clinics= new ArrayList<>();
		ArrayList<String> clinicNames= new ArrayList<>();
		for(int i=0; i<patientClinicProfile.size(); i++){//
			clinics.add(patientClinicProfile.get(i).getClinic().getId());
			clinicNames.add(patientClinicProfile.get(i).getClinic().getName());

		}
		patientForLabTech.setClinics(clinics);
		patientForLabTech.setClinicNames(clinicNames);
    	return  patientForLabTech;
	}

	public ArrayList<Test> getTestTypes(PatientForLabTech patientProfile) {
    	ArrayList<Test> tests= new ArrayList<>();
    	for(int i=0; i<patientProfile.getClinics().size(); i++){
    		ArrayList<Test> resultTests=testRepo.getTestListByClinicId(patientProfile.getClinics().get(i));
			for(int j=0; j<resultTests.size(); j++){
				tests.add(resultTests.get(j));
				System.out.println("shjkfhdfkjdhfk"+resultTests.get(j));
			}
		}

    	return  tests;
	}
}

