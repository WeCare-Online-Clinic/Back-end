package wecare.backend.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Report;
import wecare.backend.model.User;
import wecare.backend.repository.ReportRepository;
import wecare.backend.repository.UserRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private UserRepository userRepo;
		
	public List<Report> getAllReport(){
		List<Report> report = reportRepo.findAllReport();
		return report;
	}
	
	public List<Report> getReportProfileById(Integer id) {
		List<Report> report = reportRepo.getReportProfileById(id);
		return report;
	
	}
	
	public List<Report> getReportProfileByName(String name){
		List<Report> report=reportRepo.findByFirstNameLike(name);
		return report;
	}

}
