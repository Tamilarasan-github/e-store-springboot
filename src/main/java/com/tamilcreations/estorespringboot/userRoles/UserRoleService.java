package com.tamilcreations.estorespringboot.userRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.roles.Role;
import com.tamilcreations.estorespringboot.roles.RoleService;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Utils;

import io.jsonwebtoken.Claims;

@Service
public class UserRoleService
{
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private RoleService roleService;
	
	
	//private UserService userService;
	
//	@Autowired
//	public UserRoleService(UserService userService)
//	{
//		this.userService = userService;
//	}

	
	public List<UserRole> getUserRoleByUserId(Long userId)
	{
		List<UserRole> userRolesList = userRoleRepo.findUserRoleByUserId(userId);
		return userRolesList;
	}
	
	public Long getRoleIdByUserId(Long userId)
	{
		Optional<Long> userRoleOptional = userRoleRepo.findRoleIdByUserId(userId);
		
		if(userRoleOptional.isPresent())
		{
			return userRoleOptional.get();
		}
		else
		{
			throw new RuntimeException("User Role is not found");
		}
	}
	
	@Transactional
	public UserRole addRoleToUser(UserRole userRole)
	{		
		return userRoleRepo.saveAndFlush(userRole);
	}
	
	
}
