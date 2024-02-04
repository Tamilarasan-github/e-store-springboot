package com.tamilcreations.estorespringboot.productStocks;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductService;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;
import com.tamilcreations.estorespringboot.utils.Utils;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.Nullable;

@Controller
public class ProductStocksController
{	
		
	@Autowired
	private ProductStocksService productStocksService;
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
@Secured(value = {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_STOCKS_READ_ACCESS, Roles.SELLER_STOCKS_WRITE_ACCESS})
@QueryMapping
public ProductStocksResponse getProductStocksByProductUuidForCurrentTimeForLoggedInSeller(@Argument String productUuid) throws Exception
{
	Claims claims = genericService.getClaims();
	String loggedInUser = claims.get("phoneNumber").toString();

	User user = userService.getUserByPhoneNumber(loggedInUser);
	Long loggedInSellerId = user.getSellerId();

	Product product = productService.findProductByProductUuid(productUuid);
	Long productSellerId = product.getSeller().getSellerId();

	if (Long.compare(loggedInSellerId, productSellerId) == 0)
	{
		return productStocksService.getProductStocksForCurrentTime(productUuid);
	}
	else
	{
		return new ProductStocksResponse("You do not have permission to view product stocks of other Seller's Product.");
	}
}
			
	@Secured(value = {Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS})
	@QueryMapping
	public ProductStocksResponse getProductStocksByProductUuidForCurrentTime(@Argument String productUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		return productStocksService.getProductStocksForCurrentTime(productUuid);
	}
	
	
	
	@Secured(value = {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_STOCKS_READ_ACCESS, Roles.SELLER_STOCKS_WRITE_ACCESS})
	@QueryMapping
	public ProductStocksConnection getProductStocksListByProductUuidForLoggedInSeller(@Argument String productUuid, @Argument @Nullable int first, @Argument @Nullable String after, @Argument @Nullable String before) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return productStocksService.getProductStocksList(productUuid, first, after, before);
		}
		else
		{
			throw new RuntimeException("You do not have permission to view product stocks of other Seller's Product.");
		}
		
	}
	
	@Secured(value = {Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS})
	@QueryMapping
	public ProductStocksConnection getProductStocksListByProductUuid(@Argument String productUuid, @Argument @Nullable int first, @Argument @Nullable String after, @Argument @Nullable String before) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return productStocksService.getProductStocksList(productUuid, first, after, before);
			
		}
		else
		{
			throw new RuntimeException("You do not have permission to view product stocks of other Seller's Product.");
		}
		
	}
	
	@Secured(value = {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_STOCKS_WRITE_ACCESS})
	@MutationMapping
	public ProductStocksResponse addNewProductStocksForLoggedInSeller(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = productStocksInput.getProduct().getUuid();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			productStocksInput.setCreatedDate(new Timestamp(new Date().getTime()));
			productStocksInput.setUuid(UUID.randomUUID().toString());		
			
			ProductStocks newProductStocks = productStocksInput.toProductStocks();
			
			return productStocksService.addNewProductStocks(newProductStocks);
		}
		else
		{
			return new ProductStocksResponse("You do not have permission to add new product stocks for other Seller's Product.");
		}
		
	}
	
	@Secured(value= {Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS})
	@MutationMapping
	public ProductStocksResponse addNewProductStocksOnBehalfOfSeller(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = productStocksInput.getProduct().getUuid();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		
			productStocksInput.setCreatedDate(new Timestamp(new Date().getTime()));
			productStocksInput.setUuid(UUID.randomUUID().toString());		
			
			ProductStocks newProductStocks = productStocksInput.toProductStocks();
			
			return productStocksService.addNewProductStocks(newProductStocks);		
	}
	
	@Secured(value = {Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_STOCKS_WRITE_ACCESS})
	@MutationMapping
	public ProductStocksResponse updateExistingProductStocksForLoggedInSeller(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = productStocksInput.getProduct().getUuid();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			productStocksInput.setUpdatedDate(new Timestamp(new Date().getTime()));
			ProductStocks existingProductStocksDetails = productStocksService
					.getProductStocksIdByProductStocksUuid(productStocksInput.getUuid());

			ProductStocks newProductStocks = productStocksInput.toProductStocks();
			newProductStocks.setStockId(existingProductStocksDetails.getStockId());

			Date existingProductStocksEffectiveDate = existingProductStocksDetails.getStocksEffectiveDate();
			Date existingProductStocksTermDate = existingProductStocksDetails.getStocksTermDate();

			Date newProductStocksTermDate = Utils.convertStringToDateFormat(productStocksInput.getStocksTermDate());

			if (existingProductStocksTermDate == null
					|| existingProductStocksEffectiveDate.before(new Timestamp(new Date().getTime()))
							&& existingProductStocksTermDate != null)
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
			else if (existingProductStocksEffectiveDate.after(new Timestamp(new Date().getTime())))
			{
				return productStocksService.updateExistingProductStocks(newProductStocks);
			}
			else
			{
				return new ProductStocksResponse("Only ProductStocks Term Date can be updated for existing closed term date records, please create new productStocks to change other details.");
			}
		}
		else
		{
			return new ProductStocksResponse("You do not have permission to update product stocks for other Seller's Product.");
		}
	}
	
	@Secured(value= {Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS})
	@MutationMapping
	public ProductStocksResponse updateExistingProductStocksOnBehalfOfSeller(@Argument ProductStocksInput productStocksInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = productStocksInput.getProduct().getUuid();
		
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

			productStocksInput.setUpdatedDate(new Timestamp(new Date().getTime()));
			ProductStocks existingProductStocksDetails = productStocksService
					.getProductStocksIdByProductStocksUuid(productStocksInput.getUuid());

			ProductStocks newProductStocks = productStocksInput.toProductStocks();
			newProductStocks.setStockId(existingProductStocksDetails.getStockId());

			Date existingProductStocksEffectiveDate = existingProductStocksDetails.getStocksEffectiveDate();
			Date existingProductStocksTermDate = existingProductStocksDetails.getStocksTermDate();

			Date newProductStocksTermDate = Utils.convertStringToDateFormat(productStocksInput.getStocksTermDate());

			if (existingProductStocksTermDate == null
					|| existingProductStocksEffectiveDate.before(new Timestamp(new Date().getTime()))
							&& existingProductStocksTermDate != null)
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
			else if (existingProductStocksEffectiveDate.after(new Timestamp(new Date().getTime())))
			{
				return productStocksService.updateExistingProductStocks(newProductStocks);
			}
			else
			{
				return new ProductStocksResponse("Only ProductStocks Term Date can be updated for existing closed term date records, please create new productStocks to change other details.");
			}
		}
		
}
