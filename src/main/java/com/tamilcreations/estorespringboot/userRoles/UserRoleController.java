package com.tamilcreations.estorespringboot.userRoles;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.roles.Role;
import com.tamilcreations.estorespringboot.roles.RoleService;
import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserRoleController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@MutationMapping
	public UserRoleResponse addNewUserRole(@Argument UserRoleInput userRoleInput) throws Exception
	{
		User user = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		Role roleToMap = roleService.getRoleByRoleUuid(userRoleInput.getRoleUuid());
		User userToMap = userService.findUserByUserUuid(userRoleInput.getUserUuid());
		
		UserRole userRole = userRoleInput.toUserRole();
		userRole.setUuid(UUID.randomUUID().toString());
		userRole.setUserId(userToMap.getUserId());
		userRole.setRoleId(roleToMap.getRoleId());
		userRole.setDeleteFlag(false);
		userRole.setCreatedBy(user.getPhoneNumber());
		userRole.setCreatedDate(new Date());
		
		UserRole savedUserRole = userRoleService.addRoleToUser(userRole);
		
		return new UserRoleResponse(savedUserRole, "New UserRole Saved Successfully.");
	}
}
