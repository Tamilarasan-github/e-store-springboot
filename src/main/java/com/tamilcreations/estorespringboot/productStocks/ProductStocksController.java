package com.tamilcreations.estorespringboot.productStocks;

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
public class ProductStocksController
{	
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductStocksService productStocksService;
			
	@QueryMapping
	public ProductStocksResponse getProductStocksByProductIdForNow(@Argument long productId)
	{
		return productStocksService.getProductStocksForCurrentTime(productId);
	}
	
	@QueryMapping
	public ProductStocksResponse getProductStocksByProductUuidForNow(@Argument String productUuid) throws Exception
	{
		return productStocksService.getProductStocksForCurrentTime(productUuid);
	}
	
	@QueryMapping
	public ProductStocksResponse getProductStocksListByProductUuid(@Argument String productUuid) throws Exception
	{
		return productStocksService.getProductStocksList(productUuid);
	}
	
	@MutationMapping
	public ProductStocksResponse addNewProductStocks(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		productStocksInput.setCreatedDate(new Timestamp(new Date().getTime()));
		productStocksInput.setUuid(UUID.randomUUID().toString());		
		
		ProductStocks newProductStocks = productStocksInput.toProductStocks();
		
		return productStocksService.addNewProductStocks(newProductStocks);
	}
	
	@MutationMapping
	public ProductStocksResponse updateExistingProductStocks(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		productStocksInput.setUpdatedDate(new Timestamp(new Date().getTime()));
		ProductStocks existingProductStocksDetails = productStocksService.getProductStocksIdByProductStocksUuid(productStocksInput.getUuid());
		
		ProductStocks newProductStocks = productStocksInput.toProductStocks();
		newProductStocks.setStockId(existingProductStocksDetails.getStockId());
		
		Date existingProductStocksEffectiveDate = existingProductStocksDetails.getStocksEffectiveDate();
		Date existingProductStocksTermDate = existingProductStocksDetails.getStocksTermDate();
		
		Date newProductStocksTermDate = Utils.convertStringToDateFormat(productStocksInput.getStocksTermDate());
				
		if(existingProductStocksTermDate == null || existingProductStocksEffectiveDate.before(new Timestamp(new Date().getTime())) && existingProductStocksTermDate!=null)
		{
			if (newProductStocksTermDate.after(new Timestamp(new Date().getTime())))
			{
				existingProductStocksDetails.setStocksTermDate(newProductStocksTermDate);
				return productStocksService.updateExistingProductStocks(existingProductStocksDetails);
			} 
			else
			{
				return new ProductStocksResponse("ProductStocks Term Date cannot be updated to past date & time.");
			}
		}
		else if(existingProductStocksEffectiveDate.after(new Timestamp(new Date().getTime())))
		{
			return productStocksService.updateExistingProductStocks(newProductStocks);
		}
		else
		{
			return new ProductStocksResponse("Only ProductStocks Term Date can be updated for existing closed term date records, please create new productStocks to change other details.");
		}
		
	}
}
