package com.tamilcreations.estorespringboot.prices;

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
public class PriceController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PriceService priceService;
			
	@QueryMapping
	public PriceResponse getPriceByProductIdForNow(@Argument long productId)
	{
		return priceService.getPriceForCurrentTime(productId);
	}
	
	@QueryMapping
	public PriceResponse getPriceByProductUuidForNow(@Argument String productUuid) throws Exception
	{
		return priceService.getPriceForCurrentTime(productUuid);
	}
	
	@QueryMapping
	public PriceResponse getPricesListByProductUuid(@Argument String productUuid) throws Exception
	{
		return priceService.getPricesList(productUuid);
	}
	
	@MutationMapping
	public PriceResponse addNewPrice(@Argument PriceInput priceInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		priceInput.setCreatedDate(new Timestamp(new Date().getTime()));
		priceInput.setUuid(UUID.randomUUID().toString());		
		
		Price newPrice = priceInput.toPrice();
		
		return priceService.addNewPrice(newPrice);
	}
	
	@MutationMapping
	public PriceResponse updateExistingPrice(@Argument PriceInput priceInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		priceInput.setUpdatedDate(new Timestamp(new Date().getTime()));
		Price existingPriceDetails = priceService.getPriceIdByPriceUuid(priceInput.getUuid());
		
		Price newPrice = priceInput.toPrice();
		newPrice.setPriceId(existingPriceDetails.getPriceId());
		
		Date existingPriceEffectiveDate = existingPriceDetails.getPriceEffectiveDate();
		Date existingPriceTermDate = existingPriceDetails.getPriceTermDate();
		
		Date newPriceTermDate = Utils.convertStringToDateFormat(priceInput.getPriceTermDate());
				
		if(existingPriceTermDate == null || existingPriceEffectiveDate.before(new Timestamp(new Date().getTime())) && existingPriceTermDate!=null)
		{
			if (newPriceTermDate.after(new Timestamp(new Date().getTime())))
			{
				existingPriceDetails.setPriceTermDate(newPriceTermDate);
				return priceService.updateExistingPrice(existingPriceDetails);
			} 
			else
			{
				return new PriceResponse("Price Term Date cannot be updated to past date & time.");
			}
		}
		else if(existingPriceEffectiveDate.after(new Timestamp(new Date().getTime())))
		{
			return priceService.updateExistingPrice(newPrice);
		}
		else
		{
			return new PriceResponse("Only Price Term Date can be updated for existing closed term date records, please create new price to change other details.");
		}
		
	}
}
