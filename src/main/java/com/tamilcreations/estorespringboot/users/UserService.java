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
import com.tamilcreations.estorespringboot.sellers.SellerService;
import com.tamilcreations.estorespringboot.userRoles.UserRole;
import com.tamilcreations.estorespringboot.userRoles.UserRoleService;
import com.tamilcreations.estorespringboot.utils.Utils;


@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;
		
	@Autowired
	private UserRoleService userRoleService;
		
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	private RoleService roleService;
	
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
			
			roleId = userRoleService.getRoleIdByUserId(user.getUserId());
						
		
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
	
	public UserResponse authenticateUser(String phoneNumber, String password)
	{
		UserDetails userDetails = loadUserByUsername(phoneNumber);
		
		Map<String, String> claims = new HashMap<String, String>();
		
		if(validatePassword(password, userDetails.getPassword()))
		{
			boolean isUserRoleMappingFound[] = { false };
			boolean isRoleFound[] = { false };
			String userUuid = null;
			String sellerUuid = null;
			String roleName = null;
			
			Optional<User> userOptional = userRepo.findUserByPhoneNumber(phoneNumber);
			User user = userOptional.get();
			user.setPassword("");
			
			userUuid = user.getUuid();
			
			List<UserRole> userRolesList = userRoleService.getUserRoleByUserId(user.getUserId());
			
			List<String> accessList = userRolesList.stream()
				    .map(userRole -> userRole.getRole().getRoleName())
				    .collect(Collectors.toList());

						
			claims.put("phoneNumber", phoneNumber);
			claims.put("role", roleName);
			claims.put("user-uuid", userUuid);
			
			user.setRole(roleName);
			
			user.setJwtToken(JwtAuthenticationFilter.generateToken(userDetails.getUsername(), accessList, user.getUuid()));
			
			return new UserResponse(user, "Login Successful");
		}
		else
		{
			throw new RuntimeException("Unauthorized Access");
		}		
	}
	
	public UserResponse registerNewUser(UserInput userInput)
	{
		String phoneNumber = userInput.getPhoneNumber();
		Optional<User> userOptional = userRepo.findUserByPhoneNumber(phoneNumber);
		
		if (!userOptional.isPresent())
		{
			userInput.setUserId(null);
			userInput.setUuid(UUID.randomUUID().toString());
			userInput.setLastLoginDate(null);
			userInput.setUserAccountStatus("ACTIVE");
			userInput.setDeleteFlag(false);
			userInput.setCreatedBy(phoneNumber);
			userInput.setCreatedDate(new Date());

			User newUser = userInput.toUser();
			String encodedPassword = passwordEncoder.encode(newUser.getPassword());
			//System.out.println("encodedPassword:"+encodedPassword+" Size:"+encodedPassword.length());
			newUser.setPassword(encodedPassword);
					
			User savedUser = userRepo.saveAndFlush(newUser);
			
			Role role = new Role();
			role.setRoleId(Long.valueOf(1));
			
			UserRole userRole = new UserRole();
			userRole.setUser(savedUser);
			userRole.setRole(role);
			userRole.setDeleteFlag(false);
			userRole.setCreatedBy(phoneNumber);
			userRole.setCreatedDate(new Date());
			
			UserRole savedUserRole = userRoleService.addRoleToUser(userRole);
			
			List<UserRole> userRolesList = userRoleService.getUserRoleByUserId(savedUser.getUserId());
			
			List<String> accessList = userRolesList.stream()
				    .map(userRolee -> userRolee.getRole().getRoleName())
				    .collect(Collectors.toList());
			
			savedUser.setPassword(null);
			savedUser.setJwtToken(JwtAuthenticationFilter.generateToken(phoneNumber, accessList, savedUserRole.getUuid()));

			return new UserResponse(savedUser, "User created successfully");
		}
		else
		{
			return new UserResponse("Entered phone number is already registered. Please login with existing phone number or enter new phone number to register new user.");
		}
		
	}

	
}
