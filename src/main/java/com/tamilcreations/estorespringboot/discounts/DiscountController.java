package com.tamilcreations.estorespringboot.discounts;

import java.sql.Timestamp;
import java.util.Date;

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

@Controller
public class DiscountController
{	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private UserService userService;
			
			
	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_DISCOUNT_READ_ACCESS, Roles.SELLER_DISCOUNT_WRITE_ACCESS })
	@QueryMapping
	public DiscountResponse getDiscountByProductUuidForCurrentTimeForLoggedInSeller(@Argument String productUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return discountService.getDiscountForCurrentTime(productUuid);
		}
		else
		{
			return new DiscountResponse("You do not have permission to view Discounts of other Seller's Product.");
		}
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public DiscountResponse getDiscountByProductUuidForCurrentTime(@Argument String productUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		
		return discountService.getDiscountForCurrentTime(productUuid);
	}
	
	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_DISCOUNT_READ_ACCESS, Roles.SELLER_DISCOUNT_WRITE_ACCESS })
	@QueryMapping
	public DiscountResponse getDiscountsListByProductUuidForLoggedInSeller(@Argument String productUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return discountService.getDiscountsList(productUuid);
		}
		else
		{
			return new DiscountResponse("You do not have permission to view Discounts of other Seller's Product.");
		}
		
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public DiscountResponse getDiscountsListByProductUuid(@Argument String productUuid) throws Exception
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		return discountService.getDiscountsList(productUuid);
		
	}
	
	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_DISCOUNT_WRITE_ACCESS })
	@MutationMapping
	public DiscountResponse addNewDiscountForLoggedInSeller(@Argument DiscountInput discountInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = discountInput.getProduct().getUuid();
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			discountInput.setProduct(product);
			
			Discount newDiscount = Utils.applyNewCreationDefaultValues(discountInput, loggedInUser).toDiscount();
			
			return discountService.addNewDiscount(newDiscount);
		}
		else
		{
			return new DiscountResponse("You do not have permission to add new discounts to other Seller's Product.");
		}
		
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public DiscountResponse addNewDiscountOnBehalfOfSeller(@Argument DiscountInput discountInput, @Argument String sellerUuid) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		String loggedInUserName = genericService.getAuthenticatedUserName();
			
		Discount newDiscount = Utils.applyNewCreationDefaultValues(discountInput, loggedInUserName).toDiscount();
		
		return discountService.addNewDiscount(newDiscount);
	}
	
	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_DISCOUNT_WRITE_ACCESS })
	@MutationMapping
	public DiscountResponse updateExistingDiscountForLoggedInSeller(@Argument DiscountInput discountInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		String productUuid = discountInput.getProduct().getUuid();
		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{

			Discount existingDiscountDetails = discountService.getDiscountIdByDiscountUuid(discountInput.getUuid());

			Discount newDiscount = Utils.applyUpdateDefaultValues(discountInput, loggedInUser).toDiscount();
			newDiscount.setDiscountId(existingDiscountDetails.getDiscountId());

			Date existingDiscountEffectiveDate = existingDiscountDetails.getDiscountEffectiveDate();
			Date existingDiscountTermDate = existingDiscountDetails.getDiscountTermDate();

			Date newDiscountTermDate = Utils.convertStringToDateFormat(discountInput.getDiscountTermDate());

			if (existingDiscountTermDate == null
					|| existingDiscountEffectiveDate.before(new Timestamp(new Date().getTime()))
							&& existingDiscountTermDate != null)
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
			else if (existingDiscountEffectiveDate.after(new Timestamp(new Date().getTime())))
			{
				return discountService.updateExistingDiscount(newDiscount);
			} 
			else
			{
				return new DiscountResponse(
						"Only Discount Term Date can be updated for existing closed term date records, please create new discount to change other details.");
			}
		}
		else
		{
			return new DiscountResponse("You do not have permission to update discounts of other Seller's Product.");
		}
		
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public DiscountResponse updateExistingDiscountOnBehalfOfSeller(@Argument DiscountInput discountInput) throws Exception
	{
		//JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);
		String loggedInUserName = genericService.getAuthenticatedUserName();

		Discount existingDiscountDetails = discountService.getDiscountIdByDiscountUuid(discountInput.getUuid());
		
		Discount newDiscount = Utils.applyUpdateDefaultValues(discountInput, loggedInUserName).toDiscount();
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
