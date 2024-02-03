package com.tamilcreations.estorespringboot.sellers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SellerService
{
	@Autowired
	SellerRepo sellerRepo;
		
	@Transactional
	public Seller getSellerBySellerId(long sellerId)
	{
		return sellerRepo.findBySellerId(sellerId);
	}
	
	@Transactional
	public Seller getSellerBySellerUuid(String sellerUuid) throws Exception
	{
		Optional<Seller> sellerOptional = sellerRepo.findSellerBySellerUuid(sellerUuid);
		
		if(!sellerOptional.isEmpty())
		{
			return sellerOptional.get();
		}
		else
		{
			throw new Exception("Seller is not found.");
		}
	}
	
	@Transactional
	public Long getSellerIdBySellerUuid(String sellerUuid) throws Exception
	{
		Optional<Long> sellerIdOptional = sellerRepo.findSellerIdBySellerUuid(sellerUuid);
		
		if(!sellerIdOptional.isEmpty())
		{
			return sellerIdOptional.get();
		}
		else
		{
			throw new Exception("Seller is not found.");
		}
	}
}
