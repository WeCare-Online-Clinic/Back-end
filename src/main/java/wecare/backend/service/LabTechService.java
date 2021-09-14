package wecare.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.LabTechnician;
import wecare.backend.model.Patient;
import wecare.backend.model.Report;
import wecare.backend.model.Test;
import wecare.backend.repository.LabTechnicianRepository;
import wecare.backend.repository.ReportRepository;
import wecare.backend.repository.TestRepository;


@Service
public class LabTechService {
    @Autowired
    private LabTechnicianRepository labTechnicianRepo;
    @Autowired
    private TestRepository testRepo;
    @Autowired
    private ReportRepository reportRepo;

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
}

