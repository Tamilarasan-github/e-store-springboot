package com.tamilcreations.estorespringboot.users.addressdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.Roles;
import com.tamilcreations.estorespringboot.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserAddressDetailController
{
	
	@Autowired
	private HttpServletRequest request;
		
	@Autowired
	private UserAddressDetailService userAddressDetailService;
	
	@Autowired
	private UserService userService;
	
	@Secured(value ={ Roles.USER, Roles.ADMIN, Roles.CUSTOMER_SUPPORT })
	@QueryMapping
	public List<UserAddressDetail> getUserAddressDetailsForLoggedInUserId() throws Exception
	{
		User loggedInUser = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		User user = userService.findUserByUserUuid(loggedInUser.getUuid());
		return userAddressDetailService.getUserAddressDetails(user.getUserId());
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.CUSTOMER_SUPPORT })
	@QueryMapping
	public List<UserAddressDetail> getUserAddressDetailsByUserUuid(@Argument String userUuid) throws Exception
	{
		 // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check authorities
        if (authentication != null && authentication.isAuthenticated()) {
            // Get authorities (roles)
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // Print or process authorities
            for (GrantedAuthority authority : authorities) {
                System.out.println("Authority: " + authority.getAuthority());
            }
        }
		User loggedInUser = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		User user = userService.findUserByUserUuid(userUuid);
		return userAddressDetailService.getUserAddressDetails(user.getUserId());
        
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.CUSTOMER_SUPPORT })
	@MutationMapping
	public UserAddressDetailResponse addNewUserAddressDetails(@Argument UserAddressDetailInput userAddressDetailInput, @Argument String userUuid) throws Exception
	{
		User loggedInUser = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		UserAddressDetail userAddressDetail = userAddressDetailInput.toUserAddressDetail();
		User user = null;
		
		if (userUuid != null)
		{
			if (loggedInUser.getRole().equalsIgnoreCase("ADMIN"))
			{
				user = userService.findUserByUserUuid(userUuid);
			} 
			else
			{
				return new UserAddressDetailResponse("Unauthorized Access!");
			}
		}
		else
		{
			user = userService.findUserByUserUuid(userAddressDetail.getUser().getUuid());
		}
		
		userAddressDetail.setUser(user);

		userAddressDetail = Utils.applyNewCreationDefaultValues(userAddressDetail, loggedInUser.getPhoneNumber());

		return userAddressDetailService.addNewUserAddressDetails(userAddressDetail);

	}
	
	@Secured(value ={ Roles.ADMIN, Roles.CUSTOMER_SUPPORT, Roles.USER })
	@MutationMapping
	public UserAddressDetailResponse addNewUserAddressDetailsForLoggedInUserId(@Argument UserAddressDetailInput userAddressDetailInput) throws Exception
	{
		User loggedInUser = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		UserAddressDetail userAddressDetail = userAddressDetailInput.toUserAddressDetail();
		User user = null;
		
		user = userService.findUserByUserUuid(loggedInUser.getUuid());
				
		userAddressDetail.setUser(user);

		userAddressDetail = Utils.applyNewCreationDefaultValues(userAddressDetail, loggedInUser.getPhoneNumber());

		return userAddressDetailService.addNewUserAddressDetails(userAddressDetail);

	}
}
