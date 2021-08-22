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

	@GetMapping("/changeDoctorStatus/{doctorId}")
	public ResponseEntity<Integer> changeDoctorStatus(@PathVariable Integer doctorId){
		Integer result=adminService.changeDoctorStatus(doctorId);
		if(result!=null){
			return ResponseEntity.ok(result);
		}
		return  ResponseEntity.badRequest().build();



	}

	@GetMapping("/changeNurseStatus/{nurseId}")
	public ResponseEntity<Integer> changeNurseStatus(@PathVariable Integer nurseId){
		Integer result=adminService.changeNurseStatus(nurseId);
		if(result!=null){
			return ResponseEntity.ok(result);
		}
		return  ResponseEntity.badRequest().build();



	}

}
