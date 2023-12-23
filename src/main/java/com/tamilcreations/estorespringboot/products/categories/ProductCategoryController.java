package com.tamilcreations.estorespringboot.products.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class ProductCategoryController
{
	@Autowired
	ProductCategoryRepo productCategoryRepo;
	
	@Autowired
	ProductCategoryService productCategoryService;
	
	@QueryMapping				 
	public List<ProductCategory> getAllProductCategories()
	{
		return productCategoryService.getAllProductCategories();
	}
	
	
	@QueryMapping
	public List<ProductCategory> getAllActiveProductCategories()
	{
		return productCategoryService.getAllActiveProductCategories();
	}
	
	
}
