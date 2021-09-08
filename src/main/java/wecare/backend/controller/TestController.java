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
import wecare.backend.model.Test;
import wecare.backend.service.TestService;

@RestController
@RequestMapping(value = "wecare")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/getTest")
	public List<Test> getTest() {
		List<Test> report = testService.getAllTest();
		return report;
	}

	@GetMapping("/getTestProfile/{id}")
	public List<Test> getTestProfileById(@PathVariable Integer id) {
		List<Test> report = testService.getTestProfileById(id);
		return report;

	}

	@GetMapping("/getTestProfileByName/{name}")
	public List<Test> getTestProfileByName(@PathVariable String name) {
		List<Test> report = testService.getTestProfileByName(name);
		return report;

	}

}
