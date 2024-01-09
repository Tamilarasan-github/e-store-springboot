package com.tamilcreations.estorespringboot.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@QueryMapping
	public User getUser(@Argument long userId) throws Exception
	{
		return userService.getUser(userId);
	}
	
	@QueryMapping
	public UserResponse login(@Argument  String phoneNumber, @Argument String password) throws Exception
	{
		return userService.authenticateUser(phoneNumber, password);
	}
	
//	@QueryMapping
//	public User authenticateUser(@Argument  UserInput userInput) throws Exception
//	{
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String phoneNumber = userInput.getPhoneNumber();
//		String encyptedPassword = encoder.encode(userInput.getPassword());
//		return null;
//	}
	
	@MutationMapping
	public UserResponse registerNewUser(@Argument  UserInput userInput)
	{
		return userService.registerNewUser(userInput);
	}
	
}
