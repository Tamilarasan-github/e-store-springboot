package com.tamilcreations.estorespringboot.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.utils.CursorUtils;


@Service
public class ProductService
{
	@Autowired
	ProductRepo productRepo;
	
	@Transactional
	public List<Product> getAllProducts(Long sellerId, String productName, int first, String after, String before)
	{
		return productRepo.getAllProducts(sellerId, productName, first, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));
	}
	
	@Transactional
	public List<Product> getProductsByStatus(Long sellerId, String productName, String status, int first, String after, String before)
	{
		return productRepo.getProductsByStatus(sellerId, productName, status, first, CursorUtils.decodeCursor(after), CursorUtils.decodeCursor(before));
	}
	
	@Transactional
	public Product findProductByProductUuid(String productUuid) 
	{
		Optional<Product> productOptional =  productRepo.findProductByUuid(productUuid);
		
		if(!productOptional.isEmpty())
		{
			return productOptional.get();
		}
		else
		{
			throw new RuntimeException("Product not found!");
		}
	}
	
	
	@Transactional
	public Product addNewProduct(Product product)
	{
		return productRepo.save(product);
	}
}
