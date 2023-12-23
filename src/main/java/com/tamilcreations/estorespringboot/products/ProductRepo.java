package com.tamilcreations.estorespringboot.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{
	
	@Query(value="SELECT * FROM Products p where p.product_name LIKE :productName ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getAllProducts(String productName, int limit);

	@Query(value="SELECT * FROM Products p where p.product_id > :after AND p.product_name LIKE :productName ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getAllProducts(String productName, int limit, Long after);
	
	
	
	@Query(value="SELECT * FROM Products p where p.product_name LIKE %:productName% AND p.product_status = 'Active'  ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getAllActiveProducts(String productName, int limit);

	@Query(value="SELECT * FROM Products p where p.product_id > :after AND p.product_name  LIKE %:productName% AND p.product_status = 'Active'  ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getAllActiveProducts(String productName, int limit, Long after);
	
	
	@Query(value="SELECT * FROM Products p where p.product_name  LIKE %:productName% AND p.product_status = :status  ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getProductsByStatus(String productName, String status, int limit);

	@Query(value="SELECT * FROM Products p where p.product_id > :after AND p.product_name  LIKE %:productName% AND p.product_status = :status  ORDER BY p.product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getProductsByStatus(String productName, String status, int limit, Long after);

}
