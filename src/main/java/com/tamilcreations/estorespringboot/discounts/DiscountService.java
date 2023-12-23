package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import com.tamilcreations.estorespringboot.generic.GenericService;


@Service
public class DiscountService
{
	@Autowired
	DiscountRepo discountRepo;
	
	@Autowired
	GenericService genericService;
		
	public List<Discount> getDiscounts(long productId)
	{
		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");
		return discountRepo.findDiscountsByProductId(productId, currentDateAndTime);
	}
}
