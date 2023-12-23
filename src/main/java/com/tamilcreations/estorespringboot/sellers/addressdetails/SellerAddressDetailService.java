package com.tamilcreations.estorespringboot.sellers.addressdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;


@Service
public class SellerAddressDetailService
{
	@Autowired
	SellerAddressDetailRepo sellerAddressDetailRepo;
		
	public List<SellerAddressDetail> getSellerAddressDetails(long sellerId)
	{
		return sellerAddressDetailRepo.findBySellerId(sellerId);
	}
}
