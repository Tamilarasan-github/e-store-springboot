package com.tamilcreations.estorespringboot.discounts;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DiscountController
{	
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DiscountService discountService;
			
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
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		discountInput.setCreatedDate(new Timestamp(new Date().getTime()));
		discountInput.setUuid(UUID.randomUUID().toString());		
		
		Discount newDiscount = discountInput.toDiscount();
		
		return discountService.addNewDiscount(newDiscount);
	}
	
	@MutationMapping
	public DiscountResponse updateExistingDiscount(@Argument DiscountInput discountInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		discountInput.setUpdatedDate(new Timestamp(new Date().getTime()));
		Discount existingDiscountDetails = discountService.getDiscountIdByDiscountUuid(discountInput.getUuid());
		
		Discount newDiscount = discountInput.toDiscount();
		newDiscount.setDiscountId(existingDiscountDetails.getDiscountId());
		
		Date existingDiscountEffectiveDate = existingDiscountDetails.getDiscountEffectiveDate();
		Date existingDiscountTermDate = existingDiscountDetails.getDiscountTermDate();
		
		Date newDiscountTermDate = Utils.convertStringToDateFormat(discountInput.getDiscountTermDate());
				
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
