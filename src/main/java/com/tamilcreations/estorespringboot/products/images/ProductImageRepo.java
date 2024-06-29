package com.tamilcreations.estorespringboot.products.images;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long>, JpaSpecificationExecutor<ProductImage>
{
	@Query(value="SELECT * FROM ProductImages p where p.product_id = :productId AND p.delete_flag =0 ORDER BY p.image_order ASC", nativeQuery=true)
	List<ProductImage> findProductImageByProductId(String productId);
	
}
