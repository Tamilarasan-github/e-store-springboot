package com.tamilcreations.estorespringboot.userRoles;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.roles.Role;
import com.tamilcreations.estorespringboot.roles.RoleService;

import com.tamilcreations.estorespringboot.utils.Roles;




@Controller
public class UserRoleController
{	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Secured(value = {Roles.SELLER_ALL_MODULES_FULL_ACCESS} )
	@MutationMapping
	public UserRoleResponse addNewUserRoleByLoggedInSeller(@Argument List<UserRoleInput> userRoleInput) throws Exception
	{
		userRoleInput.forEach(userRole->{
			Role role = roleService.getRoleByRoleUuid(userRole.getRoleUuid());
			
			if(!role.getRoleName().startsWith("SELLER"))
			{
				throw new RuntimeException("You do not have permission to assign this role.");
			}
		});
		List<UserRole> savedUserRole = userRoleService.addRolesToUser(userRoleInput);
		
		return new UserRoleResponse(savedUserRole, "New UserRole Saved Successfully.");
	}
		
	@Secured(value = {Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS} )
	@MutationMapping
	public UserRoleResponse addNewUserRole(@Argument List<UserRoleInput> userRoleInput) throws Exception
	{
			
		List<UserRole> savedUserRole = userRoleService.addRolesToUser(userRoleInput);
		
		return new UserRoleResponse(savedUserRole, "New UserRole Saved Successfully.");
	}
	
	
}
