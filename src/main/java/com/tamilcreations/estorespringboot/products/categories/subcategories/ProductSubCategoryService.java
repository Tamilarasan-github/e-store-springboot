package com.tamilcreations.estorespringboot.products.categories.subcategories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductSubCategoryService
{
	@Autowired
	ProductSubCategoryRepo productSubCategoryRepo;
	
	@Transactional
	public List<ProductSubCategory> getAllProductSubCategories(long productCategoryId)
	{
		return productSubCategoryRepo.getAllProductSubCategories(productCategoryId);
	}
	
	@Transactional
	public List<ProductSubCategory> getAllActiveProductSubCategories(long productCategoryId)
	{
		String subCategoryStatus = "Active";
		return productSubCategoryRepo.getAllProductSubCategories(productCategoryId, subCategoryStatus);
	}
}
