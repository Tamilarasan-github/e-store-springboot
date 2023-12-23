package com.tamilcreations.estorespringboot.sellers.addressdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class SellerAddressDetailController
{
	@Autowired
	SellerAddressDetailRepo sellerAddressDetailRepo;
	
	@Autowired
	SellerAddressDetailService sellerAddressDetailService;
	
	@QueryMapping
	public List<SellerAddressDetail> getSellerAddressDetails(@Argument long sellerId)
	{
		return sellerAddressDetailService.getSellerAddressDetails(sellerId);
	}
}
