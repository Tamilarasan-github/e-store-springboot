package com.tamilcreations.estorespringboot.userRoles;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService
{
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	public UserRole getUserRoleByUserId(Long userId) throws Exception
	{
		Optional<UserRole> userRoleOptional = userRoleRepo.findUserRoleByUserId(userId);
		
		if(userRoleOptional.isPresent())
		{
			return userRoleOptional.get();
		}
		else
		{
			throw new Exception("User Role is not found");
		}
	}
	
	public Long getRoleIdByUserId(Long userId) throws Exception
	{
		Optional<Long> userRoleOptional = userRoleRepo.findRoleIdByUserId(userId);
		
		if(userRoleOptional.isPresent())
		{
			return userRoleOptional.get();
		}
		else
		{
			throw new Exception("User Role is not found");
		}
	}
	
	public UserRole addRoleToUser(UserRole userRole)
	{		
		return userRoleRepo.saveAndFlush(userRole);
	}
}
