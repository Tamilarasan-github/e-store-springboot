package com.tamilcreations.estorespringboot.discounts;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.generic.GenericService;
import com.tamilcreations.estorespringboot.discounts.Discount;
import com.tamilcreations.estorespringboot.discounts.DiscountInput;
import com.tamilcreations.estorespringboot.discounts.DiscountResponse;

@Controller
public class DiscountController
{	
	@Autowired
	DiscountService discountService;
	

	@Autowired
	GenericService genericService;
		
	@QueryMapping
	public DiscountResponse getDiscountByProductIdForNow(@Argument long productId)
	{
		return discountService.getDiscountForCurrentTime(productId);
	}
	
	@QueryMapping
	public DiscountResponse getDiscountByProductUuidForNow(@Argument String productUuid) throws Exception
	{
		return discountService.getDiscountForCurrentTime(productUuid);
	}
	
	@QueryMapping
	public DiscountResponse getDiscountsListByProductUuid(@Argument String productUuid) throws Exception
	{
		return discountService.getDiscountsList(productUuid);
	}
	
	@MutationMapping
	public DiscountResponse addNewDiscount(@Argument DiscountInput discountInput) throws Exception
	{
		discountInput.setCreatedDate(new Timestamp(new Date().getTime()));
		discountInput.setUuid(UUID.randomUUID().toString());		
		
		Discount newDiscount = discountInput.toDiscount();
		
		return discountService.addNewDiscount(newDiscount);
	}
	
	@MutationMapping
	public DiscountResponse updateExistingDiscount(@Argument DiscountInput discountInput) throws Exception
	{
		discountInput.setUpdatedDate(new Timestamp(new Date().getTime()));
		Discount existingDiscountDetails = discountService.getDiscountIdByDiscountUuid(discountInput.getUuid());
		
		Discount newDiscount = discountInput.toDiscount();
		newDiscount.setDiscountId(existingDiscountDetails.getDiscountId());
		
		Date existingDiscountEffectiveDate = existingDiscountDetails.getDiscountEffectiveDate();
		Date existingDiscountTermDate = existingDiscountDetails.getDiscountTermDate();
		
		Date newDiscountTermDate = genericService.convertStringToDateFormat(discountInput.getDiscountTermDate());
				
		if(existingDiscountTermDate == null || existingDiscountEffectiveDate.before(new Timestamp(new Date().getTime())) && existingDiscountTermDate!=null)
		{
			if (newDiscountTermDate.after(new Timestamp(new Date().getTime())))
			{
				existingDiscountDetails.setDiscountTermDate(newDiscountTermDate);
				return discountService.updateExistingDiscount(existingDiscountDetails);
			} 
			else
			{
				return new DiscountResponse("Discount Term Date cannot be updated to past date & time.");
			}
		}
		else if(existingDiscountEffectiveDate.after(new Timestamp(new Date().getTime())))
		{
			return discountService.updateExistingDiscount(newDiscount);
		}
		else
		{
			return new DiscountResponse("Only Discount Term Date can be updated for existing closed term date records, please create new discount to change other details.");
		}
		
	}
}
