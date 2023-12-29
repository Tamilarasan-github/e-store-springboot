package com.tamilcreations.estorespringboot.products.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductCategoryService
{
	@Autowired
	ProductCategoryRepo productCategoryRepo;
	
	@Transactional
	public List<ProductCategory> getAllProductCategories()
	{
		return productCategoryRepo.getAllProductCategories();
	}
	
	@Transactional
	public List<ProductCategory> getAllActiveProductCategories()
	{
		String categoryStatus ="Active";
		return productCategoryRepo.getAllProductCategories(categoryStatus);
	}
}
