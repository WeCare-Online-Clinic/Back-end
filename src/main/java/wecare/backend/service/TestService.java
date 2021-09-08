package wecare.backend.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Patient;
import wecare.backend.model.Test;
import wecare.backend.model.User;
import wecare.backend.repository.TestRepository;
import wecare.backend.repository.UserRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepo;

	@Autowired
	private UserRepository userRepo;
		
	public List<Test> getAllTest(){
		List<Test> test = testRepo.findAll();
		return test;
	}
	
	public List<Test> getReportProfileById(Integer id) {
		List<Test> test = testRepo.getTestProfileById(id);
		return test;
	
	}
	
	public List<Test> getReportProfileByName(String name){
		List<Test> test=testRepo.findByFirstNameLike(name);
		return test;
	}

}
