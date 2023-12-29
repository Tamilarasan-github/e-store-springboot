package com.tamilcreations.estorespringboot.users;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService
{
	@Autowired
	UserRepo userRepo;
		
	@Transactional
	public User getUser(long userId) throws Exception
	{
		Optional<User> userOptional =	 userRepo.findByUserId(userId);
		if(userOptional.isPresent())
		{
			return userOptional.get();
		}
		else
		{
			throw new Exception("User not found!");
		}
	}
	
	@Transactional
	public User findUserByUserUuid(String userUuid) throws Exception
	{
		Optional<User> userOptional =	userRepo.findUserByUserUuid(userUuid);
		
		if(userOptional.isPresent())
		{
			return userOptional.get();
		}
		else
		{
			throw new Exception("User not found!");
		}
		
	}
}
