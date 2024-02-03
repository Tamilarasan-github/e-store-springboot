package com.tamilcreations.estorespringboot.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductService;
import com.tamilcreations.estorespringboot.utils.CursorUtils;
import com.tamilcreations.estorespringboot.utils.Utils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;

@Service
public class PriceService
{
	@Autowired
	PriceRepo priceRepo;

	@Autowired
	ProductService productService;

	@Transactional
	public Price getPriceIdByPriceUuid(String priceUuid) 
	{
		Optional<Price> priceOptional = priceRepo.findPriceByPriceUuid(priceUuid);

		if (priceOptional.isPresent())
		{
			return priceOptional.get();
		} else
		{
			throw new RuntimeException("Price Details not found for the Price uuid.");
		}
	}

	@Transactional
	public PriceResponse getPriceForCurrentTime(long productId)
	{
		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Price> pricesOptional = priceRepo.findPriceByProductId(productId, currentDateAndTime);
		List<Price> prices = new ArrayList<Price>();

		if (pricesOptional.isPresent())
		{
			prices.add(pricesOptional.get());
			return new PriceResponse(prices, "Prices fetched sucessfully for current time " + currentDateAndTime);
		} else
		{
			return new PriceResponse("No Price Details found for today and current time " + currentDateAndTime);
		}
	}

	@Transactional
	public PriceResponse getPriceForCurrentTime(String productUuid) 
	{
		Product product = productService.findProductByProductUuid(productUuid);

		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Price> pricesOptional = priceRepo.findPriceByProductId(product.getProductId(), currentDateAndTime);

		if (pricesOptional.isPresent())
		{
			Price price = pricesOptional.get();
			return new PriceResponse(price, "Prices fetched sucessfully for current time.");
		} else
		{
			return new PriceResponse("No Price Details found for today and current time.");
		}
	}

	@Transactional
	public PriceConnection getPricesList(String productUuid,  int first, @Nullable String after, @Nullable String before) 
	{
		Product product = productService.findProductByProductUuid(productUuid);
		Long productId = product.getProductId();
		int fetch = first + 1;
				
		List<Price> prices = priceRepo.findPricesByProductId(
				productId, first, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));	
		
		List<PriceEdge> edges = prices.stream().map(price->
			new PriceEdge(CursorUtils.encodedCursorFor(price.getPriceId()).getValue(), price))
        	.collect(Collectors.toList());
		
		 PageInfo pageInfo = new DefaultPageInfo(
	        		CursorUtils.encodedCursorFor(prices.get(0).getPriceId()),  // startCursor
	        		CursorUtils.encodedCursorFor(prices.get(prices.size() - 1).getPriceId()),  // endCursor
	        		prices.size() > first,  // hasNextPage
	                after != null  // hasPreviousPage
	            );
	        
		
		return new PriceConnection(pageInfo, edges);
	}

	@Transactional
	public PriceResponse addNewPrice(Price price) 
	{
		String productUuid = price.getProduct().getUuid();
		Product product = productService.findProductByProductUuid(productUuid);

		Long productId = product.getProductId();

		int pricesAlreadyExistsCount = priceRepo.getCountOfPricesExistsForSamePeriod(productId,
				price.getPriceEffectiveDate(), price.getPriceTermDate());

		if (pricesAlreadyExistsCount == 0)
		{
			price.getProduct().setProductId(productId);
			Price savedPrice = priceRepo.saveAndFlush(price);
			List<Price> priceList = new ArrayList<Price>();
			priceList.add(savedPrice);
			return new PriceResponse(priceList, "Prices saved successfully!");
		} else
		{
			return new PriceResponse("Prices overlapping with existing Price for the same period.");
		}
	}

	@Transactional
	public PriceResponse updateExistingPrice(Price price) 
	{
		Long productId = null;
		if (price.getProduct().getProductId() == null)
		{
			String productUuid = price.getProduct().getUuid();
			Product product = productService.findProductByProductUuid(productUuid);
		}
		else
		{
			productId = price.getProduct().getProductId();
		}
		
		price.getProduct().setProductId(productId);
		
		List<Price> overlappingPriceList = priceRepo.findPricesOverlappingForSamePeriod(productId, price.getPriceEffectiveDate(), price.getPriceTermDate());
		
		for (Price overlappingPrice : overlappingPriceList)
		{
			if(overlappingPrice.getPriceId()!=price.getPriceId())
			{
				return new PriceResponse("New Price Term Date is overlapping with other Price.");
			}
		}
		
		Price savedPrice = priceRepo.saveAndFlush(price);
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(savedPrice);
		return new PriceResponse(priceList, "Price Details Updated Successfully!");

	}
}
