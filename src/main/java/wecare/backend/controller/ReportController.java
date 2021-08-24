package wecare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Report;
import wecare.backend.service.ReportService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/getReport")
	public List<Report> getReport() {
		List<Report> report = reportService.getAllReport();
		return report;
	}

	@GetMapping("/getReportProfile/{id}")
	public List<Report> getReportProfileById(@PathVariable Integer id) {
		List<Report> report = reportService.getReportProfileById(id);
		return report;

	}

	@GetMapping("/getReportProfileByName/{name}")
	public List<Report> getReportProfileByName(@PathVariable String name) {
		List<Report> report = reportService.getReportProfileByName(name);
		return report;

	}

}
