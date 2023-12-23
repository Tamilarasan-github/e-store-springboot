package com.tamilcreations.estorespringboot.products.categories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<ProductCategory>
{
	@Query(value="SELECT * FROM ProductCategories", nativeQuery=true)
	List<ProductCategory> getAllProductCategories();
	
	
	@Query(value="SELECT * FROM ProductCategories p where p.category_status=:categoryStatus", nativeQuery=true)
	List<ProductCategory> getAllProductCategories(String categoryStatus);
}
