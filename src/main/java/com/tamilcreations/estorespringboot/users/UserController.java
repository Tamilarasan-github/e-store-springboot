package com.tamilcreations.estorespringboot.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@QueryMapping
	public User getUser(@Argument long userId)
	{
		return userService.getUser(userId);
	}
}
