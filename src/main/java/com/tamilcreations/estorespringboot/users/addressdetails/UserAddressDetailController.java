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
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;
import com.tamilcreations.estorespringboot.utils.Utils;

import io.jsonwebtoken.Claims;

@Controller
public class UserAddressDetailController
{
	
	@Autowired
	private GenericService genericService;
		
	@Autowired
	private UserAddressDetailService userAddressDetailService;
	
	@Autowired
	private UserService userService;
	
	@Secured(value ={ Roles.USER })
	@QueryMapping
	public List<UserAddressDetail> getUserAddressDetailsForLoggedInUserId() throws Exception
	{
		//User loggedInUser = JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		
		return userAddressDetailService.getUserAddressDetails(user.getUserId());
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public List<UserAddressDetail> getUserAddressDetailsByUserUuid(@Argument String userUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.findUserByUserUuid(userUuid);
		return userAddressDetailService.getUserAddressDetails(user.getUserId());
        
	}
	
	
	
	@Secured(value ={Roles.USER })
	@MutationMapping
	public UserAddressDetailResponse addNewUserAddressDetailsForLoggedInUserId(@Argument UserAddressDetailInput userAddressDetailInput) throws Exception
	{
		Claims claims = genericService.getClaims();
		
		String loggedInUser = claims.get("phoneNumber").toString();
		
		User user = userService.getUserByPhoneNumber(loggedInUser);
		
		UserAddressDetail userAddressDetail = userAddressDetailInput.toUserAddressDetail();
						
		userAddressDetail.setUser(user);

		userAddressDetail = Utils.applyNewCreationDefaultValues(userAddressDetail, loggedInUser);
		
		return userAddressDetailService.addNewUserAddressDetails(userAddressDetail);

	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public UserAddressDetailResponse addNewUserAddressDetails(@Argument UserAddressDetailInput userAddressDetailInput, @Argument String userUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		
		String loggedInUser = claims.get("phoneNumber").toString();
		
		UserAddressDetail userAddressDetail = userAddressDetailInput.toUserAddressDetail();
		
		User user = userService.findUserByUserUuid(userAddressDetail.getUser().getUuid());
		
		userAddressDetail.setUser(user);

		userAddressDetail = Utils.applyNewCreationDefaultValues(userAddressDetail, loggedInUser);

		return userAddressDetailService.addNewUserAddressDetails(userAddressDetail);

	}
}
