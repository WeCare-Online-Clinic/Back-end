package wecare.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wecare.backend.exception.UserCollectionException;
import wecare.backend.model.User;
import wecare.backend.service.UserService;

@RestController
@RequestMapping(value="/wecare")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/userlogin")
	public ResponseEntity<User> getuserDetails(@RequestBody User user) throws UserCollectionException {	
		// LOG.info("START : Get a user");
		return new ResponseEntity<>(userService.getSingleUser(user), HttpStatus.OK);
	}
}
