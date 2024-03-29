package com.tamilcreations.estorespringboot.users;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.roles.Role;
import com.tamilcreations.estorespringboot.roles.RoleService;
import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.userRoles.UserRole;
import com.tamilcreations.estorespringboot.userRoles.UserRoleInput;
import com.tamilcreations.estorespringboot.userRoles.UserRoleRepo;
import com.tamilcreations.estorespringboot.userRoles.UserRoleService;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Utils;

import io.jsonwebtoken.Claims;


@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserRoleRepo userRoleRepo;
	
		
	//private UserRoleService userRoleService;
		
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	private RoleService roleService;
	 
	 @Autowired
	 private GenericService genericService;
	 
//	 @Autowired
//	 public UserService(UserRoleService userRoleService)
//	 {
//		 this.userRoleService = userRoleService;
//	 }
	
	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException
	{
		Optional<User> userOptional = userRepo.findUserByPhoneNumber(phoneNumber);
		
		if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }
		else
		{
			User user = userOptional.get(); 
			Long roleId = null;
			String roleName = null;
								
		
			roleName = roleService.getRoleNameByRoleId(roleId);
			
			//  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(roleName));
			return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), authorities);

		}
		
	}
	
	public User getUserByPhoneNumber(String phoneNumber) throws UsernameNotFoundException
	{
		Optional<User> userOptional = userRepo.findUserByPhoneNumber(phoneNumber);
		
		if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }
		else
		{
			User user = userOptional.get(); 
			return user;
			
		}
		
	}
	
	public boolean validatePassword(String enteredPassword, String storedPassword) {
			System.out.println("storedPassword:"+storedPassword+" Length:"+storedPassword.length());
         	boolean matchesFlag = passwordEncoder.matches(enteredPassword, storedPassword);
         	return matchesFlag;
    }
		
	@Transactional
	public User getUser(long userId)
	{
		Optional<User> userOptional =	 userRepo.findByUserId(userId);
		if(userOptional.isPresent())
		{
			return userOptional.get();
		}
		else
		{
			throw new RuntimeException("User not found!");
		}
	}
	
	@Transactional
	public User findUserByUserUuid(String userUuid)
	{
		Optional<User> userOptional =	userRepo.findUserByUserUuid(userUuid);
		
		if(userOptional.isPresent())
		{
			return userOptional.get();
		}
		else
		{
			throw new RuntimeException("User not found!");
		}
		
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
				User userToMap = userRepo.findUserByUserUuid(userRoleInput.getUserUuid()).get();
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
