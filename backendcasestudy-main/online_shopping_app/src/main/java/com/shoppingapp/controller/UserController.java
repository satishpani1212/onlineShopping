package com.shoppingapp.controller;

import org.slf4j.Logger;

//import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.exception.EntityNotFoundException;
import com.shoppingapp.exception.InvalidUserException;
import com.shoppingapp.exception.EntitiesDoesntMatchException;
import com.shoppingapp.exception.EntityAlreadyExistException;
import com.shoppingapp.exception.EntityCannotBeCreated;
import com.shoppingapp.model.User;
import com.shoppingapp.model.UserLogin;
import com.shoppingapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Value("${spring.version}")
	private String version;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/v1.0/shopping/register/")
	public User register(@RequestBody User user) throws Exception {
		log.info("Inside User Controller register");
		try {
			
			User newUser=userService.createNewUser(user);
			return newUser;
			
		}catch(Exception e){
			if(e.getMessage()=="User already Exists") {
				throw new EntityAlreadyExistException("User already Exists");
			}
			else if(e.getMessage()=="Cannot create the user") {
				throw new EntityCannotBeCreated("Cannot create the user");
			}
			throw new Exception(e.getMessage());
		}
		
	}
	
	@PostMapping("/v1.0/shopping/login/")
	public UserLogin login(@RequestBody UserLogin user ) throws Exception {
		log.info("Inside User Controller login");
		try {
			
			UserLogin userFound=userService.loginUser(user);
			return userFound;
			
		}catch(Exception e){
			if(e.getMessage()=="User doesnot Exists") {
				throw new EntityNotFoundException("User doesnot Exists");
			}
			else if(e.getMessage()=="selected role doesn't match with the user role") {
				throw new EntitiesDoesntMatchException("selected role doesn't match with the user role");
			}
			else if(e.getMessage()=="Invalid credentials") {
				throw new InvalidUserException("Invalid credentials");
			}
			throw new Exception(e.getMessage());
		}
	}
	
	@PutMapping("/v1.0/shopping/{customername}/forgot")
	public User forgot(@RequestBody UserLogin user,@PathVariable String customername) throws Exception {
		log.info("Inside User Controller forgot");
		try {
			
			User updatedUser=userService.updateUser(user,customername);
			return updatedUser;
			
		}catch(Exception e){
			if(e.getMessage()=="User doesnot Exists") {
				throw new EntityNotFoundException("User doesnot Exists");
			}
			throw new Exception(e.getMessage());
		}

	}
}
