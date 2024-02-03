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
	
	@Autowired
	private UserService userService;
	
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
	
	@Transactional
	public List<UserRole> addRolesToUser(List<UserRoleInput> userRolesInput)
	{	
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();
	
		List<UserRole> savedUserRoles = new ArrayList<UserRole>();
		userRolesInput.forEach(userRoleInput->{
			UserRole userRole = userRoleInput.toUserRole();
			try
			{
				Role roleToMap = roleService.getRoleByRoleUuid(userRoleInput.getRoleUuid());
				User userToMap = userService.findUserByUserUuid(userRoleInput.getUserUuid());
				userRole = Utils.applyNewCreationDefaultValues(userRole, loggedInUser);
				userRole.setUser(userToMap);
				userRole.setRole(roleToMap);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			savedUserRoles.add(userRoleRepo.saveAndFlush(userRole));
		});
		return savedUserRoles;
	}
}
