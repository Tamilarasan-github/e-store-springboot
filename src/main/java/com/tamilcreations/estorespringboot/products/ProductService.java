package com.tamilcreations.estorespringboot.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamilcreations.estorespringboot.generic.CursorUtils;


@Service
public class ProductService
{
	@Autowired
	ProductRepo productRepo;
	
	@Transactional
	public List<Product> getAllProducts(String productName, int first, String after)
	{
		if(after==null)
		{
			return productRepo.getAllProducts(productName, first);
		}
		else
		{
			return productRepo.getAllProducts(productName, first, CursorUtils.decodeCursor(after));
		}
	}
	
	@Transactional
	public List<Product> getAllActiveProducts(String productName, int first, String after)
	{
		if(after==null)
		{
			return productRepo.getAllActiveProducts(productName, first);
		}
		else
		{
			return productRepo.getAllActiveProducts(productName, first, CursorUtils.decodeCursor(after));
		}
	}
	
	@Transactional
	public Product addNewProduct(Product product)
	{
		return productRepo.save(product);
	}
}
