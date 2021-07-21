package wecare.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Doctor;
import wecare.backend.model.DoctorSchedule;
import wecare.backend.repository.DoctorRepository;
import wecare.backend.service.DoctorService;

@SpringBootTest
class WecareApplicationTests {

	@Autowired
	DoctorService doctorservice;  
	
	@Autowired
	private DoctorRepository doctorRepo;	
	

	@Test
	void contextLoads() {
	}
	
	@Test
	void saveDoctor() throws UserCollectionException {
		
		Doctor d = new Doctor();
		d.setClinicId(01);
		doctorservice.addDoctor(d);
		DoctorSchedule x = new DoctorSchedule();
		d.getDoctorSchedule().add(x);
		
		
		
		
		doctorRepo.saveAndFlush(d);
		
		
		
	}

}
