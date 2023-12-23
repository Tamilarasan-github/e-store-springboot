package com.tamilcreations.estorespringboot.products.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryService
{
	@Autowired
	ProductCategoryRepo productCategoryRepo;
	
	
	public List<ProductCategory> getAllProductCategories()
	{
		return productCategoryRepo.getAllProductCategories();
	}
	
	
	public List<ProductCategory> getAllActiveProductCategories()
	{
		String categoryStatus ="Active";
		return productCategoryRepo.getAllProductCategories(categoryStatus);
	}
}
