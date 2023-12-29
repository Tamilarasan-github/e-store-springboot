package com.tamilcreations.estorespringboot.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.generic.GenericService;
import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductRepo;

@Service
public class PriceService
{
	@Autowired
	PriceRepo priceRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	GenericService genericService;

	@Transactional
	public Price getPriceIdByPriceUuid(String priceUuid) throws Exception
	{
		Optional<Price> priceOptional = priceRepo.findPriceByPriceUuid(priceUuid);

		if (priceOptional.isPresent())
		{
			return priceOptional.get();
		} else
		{
			throw new Exception("Price Details not found for the Price uuid.");
		}
	}

	@Transactional
	public PriceResponse getPriceForCurrentTime(long productId)
	{
		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

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
	public PriceResponse getPriceForCurrentTime(String productUuid) throws Exception
	{
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);

		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product Details not found for the uuid " + productUuid);
			// return new PriceResponse("Product Details not found for the uuid
			// "+productUuid);
		}

		String currentDateAndTime = genericService.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<Price> pricesOptional = priceRepo.findPriceByProductId(product.getProductId(), currentDateAndTime);
		List<Price> prices = new ArrayList<Price>();

		if (pricesOptional.isPresent())
		{
			prices.add(pricesOptional.get());
			return new PriceResponse(prices, "Prices fetched sucessfully for current time.");
		} else
		{
			return new PriceResponse("No Price Details found for today and current time.");
		}
	}

	@Transactional
	public PriceResponse getPricesList(String productUuid) throws Exception
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
		List<Price> prices = priceRepo.findPricesByProductId(product.getProductId());

		if (prices.size() <= 0)
		{
			return new PriceResponse("No Price Details found for today and current time.");
		} else
		{
			return new PriceResponse(prices, "All available prices for this product fetched sucessfully.");
		}
	}

	@Transactional
	public PriceResponse addNewPrice(Price price) throws Exception
	{
		String productUuid = price.getProduct().getUuid();
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
	public PriceResponse updateExistingPrice(Price price) throws Exception
	{
		Long productId = null;
		if (price.getProduct().getProductId() == null)
		{
			String productUuid = price.getProduct().getUuid();
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
