package com.tamilcreations.estorespringboot.prices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import com.tamilcreations.estorespringboot.generic.GenericService;


@Service
public class PriceService
{
	@Autowired
	PriceRepo priceRepo;
	
	@Autowired
	GenericService genericService;
		
	public List<Price> getPrice(long productId)
	{
		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");
		return priceRepo.findPriceByProductId(productId, currentDateAndTime);
	}
}
