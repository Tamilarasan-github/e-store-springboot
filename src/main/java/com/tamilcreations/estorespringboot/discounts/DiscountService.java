package com.tamilcreations.estorespringboot.discounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.generic.GenericService;
import com.tamilcreations.estorespringboot.discounts.Discount;
import com.tamilcreations.estorespringboot.discounts.DiscountRepo;
import com.tamilcreations.estorespringboot.discounts.DiscountResponse;
import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductRepo;


@Service
public class DiscountService
{
	@Autowired
	DiscountRepo discountRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	GenericService genericService;

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
		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Discount> discountsOptional = discountRepo.findDiscountByProductId(productId, currentDateAndTime);

		if (discountsOptional.isPresent())
		{
			return new DiscountResponse(discountsOptional.get(), "Discounts fetched sucessfully for current time " + currentDateAndTime);
		} else
		{
			return new DiscountResponse("No Discount Details found for today and current time " + currentDateAndTime);
		}
	}

	public DiscountResponse getDiscountForCurrentTime(String productUuid) throws Exception
	{
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);

		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product Details not found for the uuid " + productUuid);
			// return new DiscountResponse("Product Details not found for the uuid
			// "+productUuid);
		}

		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

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
	public DiscountResponse getDiscountsList(String productUuid) throws Exception
	{
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);
		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product not found for the uuid!");
		}
		List<Discount> discounts = discountRepo.findDiscountsByProductId(product.getProductId());

		if (discounts.size() <= 0)
		{
			return new DiscountResponse("No Discount Details found for today and current time.");
		} else
		{
			return new DiscountResponse(discounts, "All available discounts for this product fetched sucessfully.");
		}
	}

	@Transactional
	public DiscountResponse addNewDiscount(Discount discount) throws Exception
	{
		String productUuid = discount.getProduct().getUuid();
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);

		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product not found for the uuid " + productUuid);
		}

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
			Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);
			Product product;
			
			if (productOptional.isPresent())
			{
				product = productOptional.get();
				product.getProductId();
			} else
			{
				throw new Exception("Product not found for the uuid " + productUuid);
			}
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
