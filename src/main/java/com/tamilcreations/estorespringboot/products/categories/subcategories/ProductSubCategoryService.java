package com.tamilcreations.estorespringboot.products.categories.subcategories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductSubCategoryService
{
	@Autowired
	ProductSubCategoryRepo productSubCategoryRepo;
	
	
	public List<ProductSubCategory> getAllProductSubCategories(long productCategoryId)
	{
		return productSubCategoryRepo.getAllProductSubCategories(productCategoryId);
	}
	
	public List<ProductSubCategory> getAllActiveProductSubCategories(long productCategoryId)
	{
		String subCategoryStatus = "Active";
		return productSubCategoryRepo.getAllProductSubCategories(productCategoryId, subCategoryStatus);
	}
}
