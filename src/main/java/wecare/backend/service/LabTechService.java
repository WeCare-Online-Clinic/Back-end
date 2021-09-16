package wecare.backend.service;

import java.sql.Time;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.*;
import wecare.backend.model.dto.PatientForLabTech;
import wecare.backend.model.dto.PatientReportDTO;
import wecare.backend.model.dto.PatientTest;
import wecare.backend.repository.*;

import javax.xml.crypto.Data;


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
		patientForLabTech.setId(patient.getId());
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
			}
		}

    	return  tests;
	}

	public Integer savePatientTest(PatientTest patientTest) {
		Patient patient=patientRepo.findByNic(patientTest.getPatientNIC());
		Optional<Test> resultTest=testRepo.findById(patientTest.getTestType());
		Test test=resultTest.get();

		Date date = new Date();

		Report report=new Report();
		report.setTestDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		report.setTestTime(new Time(date.getTime()));
		report.setAvailability(false);
		report.setPatient(patient);
		report.setTest(test);

	    Report savedTest=reportRepo.saveAndFlush(report);
	    if(savedTest==null){
	    	return 0;
		}
	    else {
	    	return 1;
		}


	}

    public Integer saveReport(PatientReportDTO patientReportDTO) {
    	Optional<Report> resultReport=reportRepo.findById(patientReportDTO.getReportId());
    	Report report=resultReport.get();

    	report.setData1(patientReportDTO.getField1());
    	report.setData2(patientReportDTO.getField2());
    	report.setIssuedDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    	report.setAvailability(true);

    	Report savedReport=reportRepo.saveAndFlush(report);
    	if(savedReport==null){
    		return 0;
		}
    	else {
    		return 1;
		}

    }
}

