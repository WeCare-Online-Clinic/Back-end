package wecare.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wecare.backend.model.Doctor;
import wecare.backend.model.Nurse;
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

	@PutMapping("/changeDoctorStatus")
	public ResponseEntity<Integer> changeDoctorStatus(@RequestBody Doctor doctor){
		Integer result=adminService.changeDoctorStatus(doctor);
		if(result!=null){
			return ResponseEntity.ok(result);
		}
		return  ResponseEntity.badRequest().build();



	}

	@PutMapping("/changeNurseStatus")
	public ResponseEntity<Integer> changeDoctorStatus(@RequestBody Nurse nurse){
		Integer result=adminService.changeNurseStatus(nurse);
		if(result!=null){
			return ResponseEntity.ok(result);
		}
		return  ResponseEntity.badRequest().build();



	}

}
