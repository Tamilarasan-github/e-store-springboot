package com.tamilcreations.estorespringboot.sellers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SellerService
{
	@Autowired
	SellerRepo sellerRepo;
		
	@Transactional
	public Seller getSeller(long sellerId)
	{
		return sellerRepo.findBySellerId(sellerId);
	}
}
