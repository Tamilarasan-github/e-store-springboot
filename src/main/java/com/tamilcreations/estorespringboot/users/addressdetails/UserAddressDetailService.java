package com.tamilcreations.estorespringboot.users.addressdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;


@Service
public class UserAddressDetailService
{
	@Autowired
	UserAddressDetailRepo userAddressDetailRepo;
		
	public List<UserAddressDetail> getUserAddressDetails(long userId)
	{
		return userAddressDetailRepo.findByUserId(userId);
	}
}
