package com.tamilcreations.estorespringboot.users;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserService
{
	@Autowired
	UserRepo userRepo;
		
	public User getUser(long userId)
	{
		return userRepo.findByUserId(userId);
	}
}
