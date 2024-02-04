package com.tamilcreations.estorespringboot.products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{
	@Query(value="SELECT * FROM Products p where p.uuid = :productUuid AND p.delete_flag =0 ", nativeQuery=true)
	Optional<Product> findProductByUuid(String productUuid);
	
	@Query(value="SELECT product_id FROM Products p where p.uuid = :productUuid  AND p.delete_flag =0 ", nativeQuery=true)
	Optional<Product> findProductIdByUuid(String productUuid);
	
	@Query(value="SELECT seller_id FROM Products p where p.uuid = :productUuid  AND p.delete_flag =0 ", nativeQuery=true)
	Optional<Product> findSellerIdByProductUuid(String productUuid);
	
	@Query(value="SELECT * FROM Products p "
			+ "WHERE "
			+ "CASE "
			+ "WHEN :after IS NOT NULL "
			+ "THEN product_id > :after "
			+ "AND product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND delete_flag =0 "
			+ "WHEN :before IS NOT NULL "
			+ "THEN product_id < :before "
			+ "AND product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND delete_flag =0 "
			+ "ELSE "
			+ "product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND delete_flag =0 "
			+ "END ORDER BY product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getAllProducts(Long sellerId, String productName, int limit, Long after, Long before);
	
		
	@Query(value="SELECT * FROM Products p "
			+ "WHERE "
			+ "CASE "
			+ "WHEN :after IS NOT NULL "
			+ "THEN product_id > :after "
			+ "AND product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND product_status =:status "
			+ "AND delete_flag =0 "
			+ "WHEN :before IS NOT NULL "
			+ "THEN product_id < :before "
			+ "AND product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND product_status =:status "
			+ "AND delete_flag =0 "
			+ "ELSE "
			+ "product_name LIKE %:productName% "
			+ "AND seller_id =:sellerId "
			+ "AND product_status =:status "
			+ "AND delete_flag =0 "
			+ "END ORDER BY product_id ASC LIMIT :limit", nativeQuery=true)
	List<Product> getProductsByStatus(Long sellerId, String productName, String status, int limit, Long after, Long before);

}
