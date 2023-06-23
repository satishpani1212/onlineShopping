package com.shoppingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingapp.model.Role;
import com.shoppingapp.model.User;
import com.shoppingapp.model.UserLogin;
import com.shoppingapp.repository.RoleRepository;
import com.shoppingapp.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;

	public User createNewUser(User user) throws Exception {
		log.info("inside User service create User");
		User newUser= new User();
		Role newRole=new Role();
		User existingUserCheck= userRepo.findByUserName(user.getLoginId());
		log.info("existingUserCheck: "+existingUserCheck);
		User existingUserCheck1= userRepo.findByUserEmail(user.getLoginId());
		log.info("existingUserCheck1: "+existingUserCheck1);
		if(existingUserCheck!=null || existingUserCheck1!=null) {
			throw new Exception("User already Exists");
		}
		try {
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setEmailID(user.getEmailID());
			newUser.setLoginId(user.getLoginId());
			newUser.setPassword(user.getPassword());
			newUser.setPhoneNumber(user.getPhoneNumber());
			newUser.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
			userRepo.save(newUser);
			newRole.setLoginId(newUser.getLoginId());
			newRole.setRole("CUST");
     //		newRole.setRole("Admin");
			newRole.setRoleId(sequenceGenerator.generateSequence(Role.SEQUENCE_NAME));
			roleRepo.save(newRole);
			return newUser;
		}catch (Exception e) {
			throw new Exception("Cannot create the user");
		}
	}

	public UserLogin loginUser(UserLogin user) throws Exception {
		log.info("In Login Service: "+user.toString());
		User existingUserCheck= userRepo.findByUserName(user.getLoginId());
		log.info("In Login Service:existingUserCheck:  "+existingUserCheck);
		Role userRole=roleRepo.findByLoginId(user.getLoginId());
		if(existingUserCheck==null) {
			throw new Exception("User doesnot Exists");
		}
		if(userRole==null) {
			throw new Exception("selected role doesn't match with the user role");
		}
		if((userRole.getRole().equals(user.getRole()))){
			if((existingUserCheck.getPassword()).equals(user.getPassword()))
				return user;
			else
				throw new Exception("Invalid credentials");
				
		}else {
			throw new Exception("selected role doesn't match with the user role"); 
		}

		
	}

	public User updateUser(UserLogin user, String customername) throws Exception {
		log.info("Inside User Service update");
		User existingUserCheck= userRepo.findByUserName(customername);
		if(existingUserCheck==null) {
			throw new Exception("User doesnot Exists");
		}
		existingUserCheck.setPassword(user.getPassword());
		return userRepo.save(existingUserCheck);
		
	}
}
