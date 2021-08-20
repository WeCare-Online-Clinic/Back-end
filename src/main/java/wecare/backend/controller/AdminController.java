package wecare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wecare.backend.model.Doctor;
import wecare.backend.model.dto.UserCount;
import wecare.backend.service.AdminService;

@RestController
@RequestMapping(value="wecare")
@CrossOrigin(origins="http://localhost:3000")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getUserCounts")
	public List<UserCount> getUserCounts(){
		List<UserCount> userCount = adminService.getUserCounts();
		return userCount;
	}

}
