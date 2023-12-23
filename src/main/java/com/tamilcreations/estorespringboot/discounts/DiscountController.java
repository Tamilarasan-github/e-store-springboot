package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class DiscountController
{	
	@Autowired
	DiscountService discountService;
	
	@QueryMapping
	public List<Discount> getDiscounts(@Argument long productId)
	{
		return discountService.getDiscounts(productId);
	}
}
