package com.tamilcreations.estorespringboot.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.utils.CursorUtils;
import com.tamilcreations.estorespringboot.utils.Utils;


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
	
	@Transactional
	public Product updateProduct(Product updatedProduct)
	{
		Optional<Product> existingProductOptional = productRepo.findProductByUuid(updatedProduct.getUuid());
		if(!existingProductOptional.isEmpty())
		{
			Product existingProduct = existingProductOptional.get();
			updatedProduct.setProductId(existingProduct.getProductId());
			
			//If you create a new Product instance, set its ID and the fields you want to update, 
			//and then save it, Spring Data JPA treats the entity as detached. 
			//In this case, only the fields you set on the new instance will be updated in the database. 
			//Other fields will be set to their default values or remain unchanged.
			
			return productRepo.save(updatedProduct);
			
		}
		else
		{
			throw new RuntimeException("Product not found to update.");
		}
		
	}
	
	@Transactional
	public Product updateProductDisplayPic(Product updatedProduct)
	{
		Optional<Product> existingProductOptional = productRepo.findProductByUuid(updatedProduct.getUuid());
		if(!existingProductOptional.isEmpty())
		{
			Product existingProduct = existingProductOptional.get();
			
			//If you create a new Product instance, set its ID and the fields you want to update, 
			//and then save it, Spring Data JPA treats the entity as detached. 
			//In this case, only the fields you set on the new instance will be updated in the database. 
			//Other fields will be set to their default values or remain unchanged.
		
			existingProduct.setImageUrl(updatedProduct.getImageUrl());
			return productRepo.save(existingProduct);
			
			
		}
		else
		{
			throw new RuntimeException("Product not found to update.");
		}
		
	}
	
	@Transactional
	public void deleteProduct(Long productId)
	{
		productRepo.deleteById(productId);
	}
}
