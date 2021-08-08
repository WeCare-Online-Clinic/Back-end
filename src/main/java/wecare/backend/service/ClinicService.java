package wecare.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import wecare.backend.model.Clinic;
import wecare.backend.model.ClinicSchedule;
import wecare.backend.repository.ClinicRepository;
import wecare.backend.repository.ClinicScheduleRepository;

@Service
public class ClinicService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClinicService.class);


	@Autowired
	private ClinicRepository clinicRepo;
	private ClinicScheduleRepository clinicScheduleRepo;

	public List<Clinic> getClinics(){
		List<Clinic> clinics = clinicRepo.findAll();
		return clinics;
	}

	public List<ClinicSchedule> getClinicsSchedule(String clinic){
		LOG.info("START : clinic Days request {}",clinic);
		List<ClinicSchedule> clinics = clinicScheduleRepo.findAll();
		return clinics;

	}
}
