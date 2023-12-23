package com.tamilcreations.estorespringboot.prices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class PriceController
{
	@Autowired
	PriceService priceService;
	
	@QueryMapping
	public List<Price> getPrice(@Argument long productId)
	{
		return priceService.getPrice(productId);
	}
}
