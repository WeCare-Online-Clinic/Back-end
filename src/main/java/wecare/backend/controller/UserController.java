package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.Doctor;
import wecare.backend.model.User;
import wecare.backend.service.DoctorService;
import wecare.backend.service.UserService;

@RestController
@RequestMapping(value="/wecare")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired 
	private UserService userService;

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/userlogin")
	public ResponseEntity<User> getuserDetails(@RequestBody User user) throws UserCollectionException {	
		//LOG.info("START : Get a user");
		return new ResponseEntity<>(userService.getSingleUser(user), HttpStatus.OK);
	}

	@GetMapping("user/info/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) throws UserCollectionException {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping("user/set_password")
	public ResponseEntity<User> setPassword(@RequestBody User user){
		return new ResponseEntity<>(userService.setPassword(user), HttpStatus.OK);
	}
}
