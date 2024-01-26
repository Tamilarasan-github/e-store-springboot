package com.tamilcreations.estorespringboot.productStocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductRepo;
import com.tamilcreations.estorespringboot.utils.Utils;


@Service
public class ProductStocksService
{
	@Autowired
	ProductStocksRepo productStocksRepo;

	@Autowired
	ProductRepo productRepo;


	@Transactional
	public ProductStocks getProductStocksIdByProductStocksUuid(String productStocksUuid) throws Exception
	{
		Optional<ProductStocks> productStocksOptional = productStocksRepo.findProductStocksByProductStocksUuid(productStocksUuid);

		if (productStocksOptional.isPresent())
		{
			return productStocksOptional.get();
		} else
		{
			throw new Exception("ProductStocks Details not found for the ProductStocks uuid.");
		}
	}

	@Transactional
	public ProductStocksResponse getProductStocksForCurrentTime(long productId)
	{
		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<ProductStocks> productStocksOptional = productStocksRepo.findProductStocksByProductId(productId, currentDateAndTime);

		if (productStocksOptional.isPresent())
		{
			return new ProductStocksResponse(productStocksOptional.get(), "ProductStocks fetched sucessfully for current time " + currentDateAndTime);
		} else
		{
			return new ProductStocksResponse("No ProductStocks Details found for today and current time " + currentDateAndTime);
		}
	}

	@Transactional
	public ProductStocksResponse getProductStocksForCurrentTime(String productUuid) throws Exception
	{
		Optional<Product> productOptional = productRepo.findProductByUuid(productUuid);

		Product product;
		if (productOptional.isPresent())
		{
			product = productOptional.get();
		} else
		{
			throw new Exception("Product Details not found for the uuid " + productUuid);
			// return new ProductStocksResponse("Product Details not found for the uuid
			// "+productUuid);
		}

		String currentDateAndTime = Utils.getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");

		Optional<ProductStocks> productStocksOptional = productStocksRepo.findProductStocksByProductId(product.getProductId(), currentDateAndTime);

		if (productStocksOptional.isPresent())
		{
			return new ProductStocksResponse(productStocksOptional.get(), "ProductStocks fetched sucessfully for current time.");
		} else
		{
			return new ProductStocksResponse("No ProductStocks Details found for today and current time.");
		}
	}

	@Transactional
	public ProductStocksResponse getProductStocksList(String productUuid) throws Exception
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
		List<ProductStocks> productStocks = productStocksRepo.findProductStocksByProductId(product.getProductId());

		if (productStocks.size() <= 0)
		{
			return new ProductStocksResponse("No ProductStocks Details found for today and current time.");
		} else
		{
			return new ProductStocksResponse(productStocks, "All available productStocks for this product fetched sucessfully.");
		}
	}

	@Transactional
	public ProductStocksResponse addNewProductStocks(ProductStocks productStocks) throws Exception
	{
		String productUuid = productStocks.getProduct().getUuid();
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

		int productStocksAlreadyExistsCount = productStocksRepo.getCountOfProductStocksExistsForSamePeriod(productId,
				productStocks.getStocksEffectiveDate(), productStocks.getStocksTermDate());

		if (productStocksAlreadyExistsCount == 0)
		{
			productStocks.getProduct().setProductId(productId);
			ProductStocks savedProductStocks = productStocksRepo.saveAndFlush(productStocks);
			List<ProductStocks> productStocksList = new ArrayList<ProductStocks>();
			productStocksList.add(savedProductStocks);
			return new ProductStocksResponse(productStocksList, "ProductStocks saved successfully!");
		} else
		{
			return new ProductStocksResponse("ProductStocks overlapping with existing ProductStocks for the same period.");
		}
	}

	@Transactional
	public ProductStocksResponse updateExistingProductStocks(ProductStocks productStocks) throws Exception
	{
		Long productId = null;
		if (productStocks.getProduct().getProductId() == null)
		{
			String productUuid = productStocks.getProduct().getUuid();
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
			productId = productStocks.getProduct().getProductId();
		}
		
		productStocks.getProduct().setProductId(productId);
		
		List<ProductStocks> overlappingProductStocksList = productStocksRepo.findProductStocksOverlappingForSamePeriod(productId, productStocks.getStocksEffectiveDate(), productStocks.getStocksTermDate());
		
		for (ProductStocks overlappingProductStocks : overlappingProductStocksList)
		{
			if(overlappingProductStocks.getStockId()!=productStocks.getStockId())
			{
				return new ProductStocksResponse("New ProductStocks Term Date is overlapping with other ProductStocks.");
			}
		}
		
		ProductStocks savedProductStocks = productStocksRepo.saveAndFlush(productStocks);
		List<ProductStocks> productStocksList = new ArrayList<ProductStocks>();
		productStocksList.add(savedProductStocks);
		return new ProductStocksResponse(productStocksList, "ProductStocks Details Updated Successfully!");

	}
}
