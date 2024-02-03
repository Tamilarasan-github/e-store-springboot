package com.tamilcreations.estorespringboot.prices;

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
import com.tamilcreations.estorespringboot.security.JwtAuthenticationFilter;
import com.tamilcreations.estorespringboot.users.User;
import com.tamilcreations.estorespringboot.users.UserService;
import com.tamilcreations.estorespringboot.utils.GenericService;
import com.tamilcreations.estorespringboot.utils.Roles;
import com.tamilcreations.estorespringboot.utils.Utils;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PriceController
{
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private GenericService genericService;

	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRICE_READ_ACCESS, Roles.SELLER_PRICE_WRITE_ACCESS })
	@QueryMapping
	public PriceResponse getPriceByProductUuidForCurrentTimeForLoggedInSeller(@Argument String productUuid) 
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return priceService.getPriceForCurrentTime(productUuid);
		}
		else
		{
			return new PriceResponse("You do not have permission to view Prices of other Seller's Product.");
		}
		
	}

	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRICE_READ_ACCESS, Roles.SELLER_PRICE_WRITE_ACCESS })
	@QueryMapping
	public PriceConnection getPricesListByProductUuidForLoggedInSeller(@Argument String productUuid, @Argument @Nullable int first, @Argument @Nullable String after, @Argument @Nullable String before) 
	{
		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(productUuid);
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			return priceService.getPricesList(productUuid, first, after, before);
		}
		else
		{
			throw new RuntimeException("You do not have permission to view Prices of other Seller's Product.");
		}
	}
	
	@Secured(value ={ Roles.SUPER_ADMIN, Roles.ADMIN, Roles.CUSTOMER_SUPPORT_READ_ACCESS, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@QueryMapping
	public PriceConnection getPricesListByProductUuid(@Argument String productUuid, @Argument @Nullable int first, @Argument @Nullable String after, @Argument @Nullable String before) 
	{
//		Claims claims = genericService.getClaims();
//		String loggedInUser = claims.get("phoneNumber").toString();

//		User user = userService.getUserByPhoneNumber(loggedInUser);
//		Long loggedInSellerId = user.getSellerId();
//
//		Product product = productService.findProductByProductUuid(productUuid);
//		Long productSellerId = product.getSeller().getSellerId();

		
		return priceService.getPricesList(productUuid, first, after, before);

	}

	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRICE_WRITE_ACCESS })
	@MutationMapping
	public PriceResponse addNewPriceForLoggedInSeller(@Argument PriceInput priceInput) 
	{
//		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(priceInput.getProduct().getUuid());
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			priceInput.setProduct(product);

			Price newPrice = Utils.applyUpdateDefaultValues(priceInput, loggedInUser).toPrice();

			return priceService.addNewPrice(newPrice);

		}
		else
		{
			return new PriceResponse("You do not have permission to add/update Price to other Seller's Product.");
		}
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public PriceResponse addNewPriceOnBehalfOfSeller(@Argument PriceInput priceInput) 
	{
//		JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(priceInput.getProduct().getUuid());
		Long productSellerId = product.getSeller().getSellerId();

		
			priceInput.setProduct(product);

			Price newPrice = Utils.applyUpdateDefaultValues(priceInput, loggedInUser).toPrice();

			return priceService.addNewPrice(newPrice);

	}

	@Secured(value ={ Roles.SELLER_ALL_MODULES_FULL_ACCESS, Roles.SELLER_PRICE_WRITE_ACCESS })
	@MutationMapping
	public PriceResponse updateExistingPriceForLoggedInSeller(@Argument PriceInput priceInput) 
	{
		// JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(priceInput.getProduct().getUuid());
		Long productSellerId = product.getSeller().getSellerId();

		if (Long.compare(loggedInSellerId, productSellerId) == 0)
		{
			priceInput.setProduct(product);

			Price existingPriceDetails = priceService.getPriceIdByPriceUuid(priceInput.getUuid());

			Price newPrice = Utils.applyUpdateDefaultValues(priceInput, loggedInUser).toPrice();
			newPrice.setPriceId(existingPriceDetails.getPriceId());

			Date existingPriceEffectiveDate = existingPriceDetails.getPriceEffectiveDate();
			Date existingPriceTermDate = existingPriceDetails.getPriceTermDate();

			Date newPriceTermDate = Utils.convertStringToDateFormat(priceInput.getPriceTermDate());

			if (existingPriceTermDate == null || existingPriceEffectiveDate.before(new Timestamp(new Date().getTime()))
					&& existingPriceTermDate != null)
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
			else if (existingPriceEffectiveDate.after(new Timestamp(new Date().getTime())))
			{
				return priceService.updateExistingPrice(newPrice);
			}
			else
			{
				return new PriceResponse(
						"Only Price Term Date can be updated for existing closed term date records, please create new price to change other details.");
			}
		}
		else
		{
			return new PriceResponse("You do not have permission to add/update Price to other Seller's Product.");
		}
	}
	
	@Secured(value ={ Roles.ADMIN, Roles.SUPER_ADMIN, Roles.CUSTOMER_SUPPORT_WRITE_ACCESS })
	@MutationMapping
	public PriceResponse updateExistingPriceOnBehalfOfSeller(@Argument PriceInput priceInput) 
	{
		// JwtAuthenticationFilter.getAuthorizationHeaderValueAndValidate(request);

		Claims claims = genericService.getClaims();
		String loggedInUser = claims.get("phoneNumber").toString();

		User user = userService.getUserByPhoneNumber(loggedInUser);
		Long loggedInSellerId = user.getSellerId();

		Product product = productService.findProductByProductUuid(priceInput.getProduct().getUuid());
		Long productSellerId = product.getSeller().getSellerId();

		priceInput.setProduct(product);
		
			Price existingPriceDetails = priceService.getPriceIdByPriceUuid(priceInput.getUuid());
			Price newPrice = Utils.applyUpdateDefaultValues(priceInput, loggedInUser).toPrice();
			newPrice.setPriceId(existingPriceDetails.getPriceId());

			Date existingPriceEffectiveDate = existingPriceDetails.getPriceEffectiveDate();
			Date existingPriceTermDate = existingPriceDetails.getPriceTermDate();

			Date newPriceTermDate = Utils.convertStringToDateFormat(priceInput.getPriceTermDate());

			if (existingPriceTermDate == null || existingPriceEffectiveDate.before(new Timestamp(new Date().getTime()))
					&& existingPriceTermDate != null)
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
			else if (existingPriceEffectiveDate.after(new Timestamp(new Date().getTime())))
			{
				return priceService.updateExistingPrice(newPrice);
			}
			else
			{
				return new PriceResponse(
						"Only Price Term Date can be updated for existing closed term date records, please create new price to change other details.");
			}
		}
		
	
}
