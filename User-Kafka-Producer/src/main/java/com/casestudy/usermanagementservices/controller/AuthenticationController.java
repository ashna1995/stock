package com.casestudy.usermanagementservices.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.casestudy.usermanagementservices.model.User;
import com.casestudy.usermanagementservices.service.DataPublisher;
import com.casestudy.usermanagementservices.service.UserServiceImpl;

@RestController
@RequestMapping("auth/v1")
public class AuthenticationController 
{
	
	@Autowired
	DataPublisher dp;

  
	private UserServiceImpl userServiceImpl;

	@Autowired
	public AuthenticationController(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}
	@GetMapping("/")
	public String serviceStarted()
	{
		return "Authentication Service Started";
	}
	

//---------------------------------------------------------------------------------------------------------	
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody User user)
	{
		try
		{
	
			String result = user.getUsername();
			dp.setTemp(result);  		

		}
		catch(Exception e)
		{
			
			return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	//----------------------------------------------------------------------------------------
	
	@PostMapping(value="/addUser", consumes="application/json; charset=utf-8")
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		if(userServiceImpl.addUser(user)!=null)
		{
			
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Data not inserted!", HttpStatus.CONFLICT);
	}
	
	
}




