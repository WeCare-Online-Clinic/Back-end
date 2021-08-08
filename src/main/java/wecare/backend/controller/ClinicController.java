package wecare.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.service.ClinicService;

@RestController
@RequestMapping(value="wecare", headers = "Accept=application/json")
@CrossOrigin(origins="http://localhost:3000")
public class ClinicController {
	
//	private static final Logger LOG = LoggerFactory.getLogger(ClinicController.class);

		@Autowired
		private ClinicService clinicService;
		
		
//		@GetMapping("/getClinicDays")		
//		public List<ClinicSchedule> getShedules(@RequestBody ClinicSheduleRequest clinicSheduleRequest) throws UserCollectionException{
//			LOG.info("START : clinic Days request {}",clinicSheduleRequest);
//			List<ClinicSchedule> clinics= clinicService.getClinicsSchedule(clinicSheduleRequest);
//			LOG.info("START : clinic Days {}",clinics);
//			return clinics;
//		}
		
		@GetMapping("/getClinicDays/{clinic}")		
		public List<ClinicSchedule> getShedules(@PathVariable String clinic){
//			LOG.info("START : clinic Days request {}",clinic);
			List<ClinicSchedule> clinics= clinicService.getClinicsSchedule(clinic);
//			LOG.info("START : clinic Days {}",clinics);
			return clinics;
		}

		@GetMapping("/getClinics")
		public List<Clinic> getClinics(){
			List<Clinic> clinics = clinicService.getClinics();
			return clinics;
		}
}

