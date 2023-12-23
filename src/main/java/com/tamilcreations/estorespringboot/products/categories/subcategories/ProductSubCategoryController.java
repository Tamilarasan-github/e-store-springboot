package com.tamilcreations.estorespringboot.products.categories.subcategories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class ProductSubCategoryController
{
	@Autowired
	ProductSubCategoryRepo productSubCategoryRepo;
	
	@Autowired
	ProductSubCategoryService productSubCategoryService;
	
	@QueryMapping
	public List<ProductSubCategory> getAllProductSubCategories(long productCategoryId)
	{
		return productSubCategoryService.getAllProductSubCategories(productCategoryId);
	}
	
	@QueryMapping
	public List<ProductSubCategory> getAllActiveProductSubCategories(long productCategoryId)
	{
		return productSubCategoryService.getAllActiveProductSubCategories(productCategoryId);
	}
	
}
