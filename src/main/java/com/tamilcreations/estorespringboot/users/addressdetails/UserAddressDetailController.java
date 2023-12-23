package com.tamilcreations.estorespringboot.users.addressdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserAddressDetailController
{
	@Autowired
	UserAddressDetailRepo userAddressDetailRepo;
	
	@Autowired
	UserAddressDetailService userAddressDetailService;
	
	@QueryMapping
	public List<UserAddressDetail> getUserAddressDetails(@Argument long userId)
	{
		return userAddressDetailService.getUserAddressDetails(userId);
	}
}
