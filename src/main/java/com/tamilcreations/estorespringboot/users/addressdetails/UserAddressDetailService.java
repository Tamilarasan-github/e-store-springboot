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
	
	public List<UserAddressDetail> getUserAddressDetailsByUserAddressUuid(String userAddressUuid)
	{
		return userAddressDetailRepo.findUserAddressDetailsByUserAddressUuid(userAddressUuid);
	}
	
	public UserAddressDetailResponse addNewUserAddressDetails(UserAddressDetail UserAddressDetail)
	{
		
		UserAddressDetail userAddressDetailSaved = userAddressDetailRepo.saveAndFlush(UserAddressDetail);
		
		return new UserAddressDetailResponse(userAddressDetailSaved, "User Address Details Saved Successfully.");
	}
}
