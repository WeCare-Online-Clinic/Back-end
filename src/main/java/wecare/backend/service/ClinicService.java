package wecare.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import wecare.backend.model.ClinicSchedule;
import wecare.backend.repository.ClinicRepository;

@Service
public class ClinicService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClinicService.class);


	@Autowired
	private ClinicRepository clinicRepo;
	
	public List<ClinicSchedule> getClinicsSchedule(String clinic){	
		LOG.info("START : clinic Days request {}",clinic);
		List<ClinicSchedule> clinics = clinicRepo.findByClinicName(clinic);
		return clinics;
		
	}
	
}
