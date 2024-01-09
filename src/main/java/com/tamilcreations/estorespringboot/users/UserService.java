package com.tamilcreations.estorespringboot.users;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.generic.JwtToken;


@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
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
			//  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), authorities);

		}
		
	}
	
	public boolean validatePassword(String enteredPassword, String storedPassword) {
			System.out.println("storedPassword:"+storedPassword+" Length:"+storedPassword.length());
         	boolean matchesFlag = passwordEncoder.matches(enteredPassword, storedPassword);
         	return matchesFlag;
    }
		
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
	
	public UserResponse authenticateUser(String phoneNumber, String password)
	{
		UserDetails userDetails = loadUserByUsername(phoneNumber);
		
		if(validatePassword(password, userDetails.getPassword()))
		{
			Optional<User> userOptional = userRepo.findUserByPhoneNumber(phoneNumber);
			User user = userOptional.get();
			user.setPassword("");
			user.setJwtToken(JwtToken.generateToken(userDetails.getUsername()));
			return new UserResponse(user, "Login Successful");
		}
		else
		{
			return new UserResponse("Unauthorized Access");
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
			savedUser.setPassword(null);
			savedUser.setJwtToken(JwtToken.generateToken(phoneNumber));

			return new UserResponse(savedUser, "User created successfully");
		}
		else
		{
			return new UserResponse("Entered phone number is already registered. Please login with existing phone number or enter new phone number to register new user.");
		}
		
	}

	
}
