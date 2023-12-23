package com.tamilcreations.estorespringboot.products.categories.subcategories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductSubCategoryRepo extends JpaRepository<ProductSubCategory, Long>, JpaSpecificationExecutor<ProductSubCategory>
{
	@Query(value="SELECT * FROM ProductSubCategories p where p.product_category_id = :productCategoryId", nativeQuery=true)
	List<ProductSubCategory> getAllProductSubCategories(long productCategoryId); 
	
	
	@Query(value="SELECT * FROM ProductSubCategories p where p.product_category_id = :productCategoryId AND sub_category_status = :subCategoryStatus", nativeQuery=true)
	List<ProductSubCategory> getAllProductSubCategories(long productCategoryId, String subCategoryStatus); 

}
