package com.tamilcreations.estorespringboot.discounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.prices.Price;
import com.tamilcreations.estorespringboot.prices.PriceConnection;
import com.tamilcreations.estorespringboot.prices.PriceEdge;
import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductService;
import com.tamilcreations.estorespringboot.utils.CursorUtils;
import com.tamilcreations.estorespringboot.utils.Utils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;


@Service
public class DiscountService
{
	@Autowired
	DiscountRepo discountRepo;

	@Autowired
	ProductService productService;

	@Transactional
	public Discount getDiscountIdByDiscountUuid(String discountUuid) throws Exception
	{
		Optional<Discount> discountOptional = discountRepo.findDiscountByDiscountUuid(discountUuid);

		if (discountOptional.isPresent())
		{
			return discountOptional.get();
		} else
		{
			throw new Exception("Discount Details not found for the Discount uuid.");
		}
	}

	@Transactional
	public DiscountResponse getDiscountForCurrentTime(long productId)
	{
		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Discount> discountsOptional = discountRepo.findDiscountByProductId(productId, currentDateAndTime);

		if (discountsOptional.isPresent())
		{
			return new DiscountResponse(discountsOptional.get(), "Discounts fetched sucessfully for current time " + currentDateAndTime);
		} else
		{
			return new DiscountResponse("No Discount Details found for today and current time " + currentDateAndTime);
		}
	}

	public DiscountResponse getDiscountForCurrentTime(String productUuid)
	{
		Product product = productService.findProductByProductUuid(productUuid);

		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Discount> discountsOptional = discountRepo.findDiscountByProductId(product.getProductId(), currentDateAndTime);

		if (discountsOptional.isPresent())
		{
			return new DiscountResponse(discountsOptional.get(), "Discounts fetched sucessfully for current time.");
		} else
		{
			return new DiscountResponse("No Discount Details found for today and current time.");
		}
	}

	@Transactional
	public DiscountConnection getDiscountsList(String productUuid,  int first, @Nullable String after, @Nullable String before) throws Exception
	{
		Product product = productService.findProductByProductUuid(productUuid);
		Long productId = product.getProductId();
		int fetch = first + 1;
				
		List<Discount> discounts = discountRepo.findDiscountsByProductId(
				productId, first, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));	
		
		List<DiscountEdge> edges = discounts.stream().map(discount->
			new DiscountEdge(CursorUtils.encodedCursorFor(discount.getDiscountId()).getValue(), discount))
        	.collect(Collectors.toList());
		
		 PageInfo pageInfo = new DefaultPageInfo(
	        		CursorUtils.encodedCursorFor(discounts.get(0).getDiscountId()),  // startCursor
	        		CursorUtils.encodedCursorFor(discounts.get(discounts.size() - 1).getDiscountId()),  // endCursor
	        		discounts.size() > first,  // hasNextPage
	                after != null  // hasPreviousPage
	            );
	        
		
		return new DiscountConnection(pageInfo, edges);
		
	}

	@Transactional
	public DiscountResponse addNewDiscount(Discount discount) throws Exception
	{
		String productUuid = discount.getProduct().getUuid();
		Product product = productService.findProductByProductUuid(productUuid);

		Long productId = product.getProductId();

		int discountsAlreadyExistsCount = discountRepo.getCountOfDiscountsExistsForSamePeriod(productId,
				discount.getDiscountEffectiveDate(), discount.getDiscountTermDate());

		if (discountsAlreadyExistsCount == 0)
		{
			discount.getProduct().setProductId(productId);
			Discount savedDiscount = discountRepo.saveAndFlush(discount);
			List<Discount> discountList = new ArrayList<Discount>();
			discountList.add(savedDiscount);
			return new DiscountResponse(discountList, "Discounts saved successfully!");
		} else
		{
			return new DiscountResponse("Discounts overlapping with existing Discount for the same period.");
		}
	}

	@Transactional
	public DiscountResponse updateExistingDiscount(Discount discount) throws Exception
	{
		Long productId = null;
		if (discount.getProduct().getProductId() == null)
		{
			String productUuid = discount.getProduct().getUuid();
			Product product = productService.findProductByProductUuid(productUuid);
		}
		else
		{
			productId = discount.getProduct().getProductId();
		}
		
		discount.getProduct().setProductId(productId);
		
		List<Discount> overlappingDiscountList = discountRepo.findDiscountsOverlappingForSamePeriod(productId, discount.getDiscountEffectiveDate(), discount.getDiscountTermDate());
		
		for (Discount overlappingDiscount : overlappingDiscountList)
		{
			if(overlappingDiscount.getDiscountId()!=discount.getDiscountId())
			{
				return new DiscountResponse("New Discount Term Date is overlapping with other Discount.");
			}
		}
		
		Discount savedDiscount = discountRepo.saveAndFlush(discount);
		List<Discount> discountList = new ArrayList<Discount>();
		discountList.add(savedDiscount);
		return new DiscountResponse(discountList, "Discount Details Updated Successfully!");

	}
}
