package com.tamilcreations.estorespringboot.productStocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.prices.Price;
import com.tamilcreations.estorespringboot.prices.PriceConnection;
import com.tamilcreations.estorespringboot.prices.PriceEdge;
import com.tamilcreations.estorespringboot.products.Product;
import com.tamilcreations.estorespringboot.products.ProductService;
import com.tamilcreations.estorespringboot.utils.CursorUtils;
import com.tamilcreations.estorespringboot.utils.Utils;

import graphql.relay.DefaultPageInfo;
import graphql.relay.PageInfo;
import io.micrometer.common.lang.Nullable;


@Service
public class ProductStocksService
{
	@Autowired
	ProductStocksRepo productStocksRepo;

	@Autowired
	ProductService productService;


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
	public ProductStocksResponse getProductStocksForCurrentTime(String productUuid)
	{
		Product product= productService.findProductByProductUuid(productUuid);

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
	public ProductStocksConnection getProductStocksList(String productUuid,  int first, @Nullable String after, @Nullable String before) throws Exception
	{
		Product product= productService.findProductByProductUuid(productUuid);
		
		Long productId = product.getProductId();
		int fetch = first + 1;
				
		List<ProductStocks> productStocks = productStocksRepo.findProductStocksByProductId(
				productId, fetch, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));	
		
		List<ProductStocksEdge> edges = productStocks.stream().map(productStock->
			new ProductStocksEdge(CursorUtils.encodedCursorFor(productStock.getStockId()).getValue(), productStock))
        	.collect(Collectors.toList());
		
		 PageInfo pageInfo = new DefaultPageInfo(
	        		CursorUtils.encodedCursorFor(productStocks.get(0).getStockId()),  // startCursor
	        		CursorUtils.encodedCursorFor(productStocks.get(productStocks.size() - 1).getStockId()),  // endCursor
	        		productStocks.size() > first,  // hasNextPage
	                after != null  // hasPreviousPage
	            );
		return new ProductStocksConnection(pageInfo, edges);
		
	}

	@Transactional
	public ProductStocksResponse addNewProductStocks(ProductStocks productStocks)
	{
		String productUuid = productStocks.getProduct().getUuid();
		Product product= productService.findProductByProductUuid(productUuid);

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
			Product product= productService.findProductByProductUuid(productUuid);
			
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
