package com.tamilcreations.estorespringboot.prices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tamilcreations.estorespringboot.productDetailView.ProductDetailView;

public interface PriceRepo extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price>
{
	@Query(value="SELECT * FROM Prices p WHERE p.uuid = :priceUuid AND p.delete_flag =0 ", nativeQuery=true)
	Optional<Price> findPriceByPriceUuid(String priceUuid);
			
	@Query(value="SELECT * FROM Prices p WHERE p.product_id = :productId AND p.delete_flag =0  AND (price_effective_date <= :currentDateAndTime AND price_term_date >= :currentDateAndTime) OR  (price_effective_date <= :currentDateAndTime AND price_term_date = NULL)", nativeQuery=true)
	Optional<Price> findPriceByProductId(long productId, String currentDateAndTime);
	
	Optional<Price> findByUuid(String uuid);
		
	@Query(value = "SELECT * FROM Prices WHERE CASE WHEN :after IS NOT NULL THEN price_id > :after AND product_id =:productId AND delete_flag =0 WHEN :before IS NOT NULL THEN price_id < :before AND product_id =:productId AND delete_flag =0 ELSE product_id =:productId AND delete_flag =0 END ORDER BY price_id ASC LIMIT :limit", nativeQuery = true)
	List<Price> findPricesByProductId(@Param("productId") Long productId, @Param("limit") int limit, @Param("after") Long after, @Param("before") Long before);
	
	@Query(value="SELECT * FROM Prices p WHERE p.product_id = :productId", nativeQuery=true)
	List<Price> findPricesByProductId(Long productId);
	
	@Query(value ="SELECT count(*) FROM Prices p WHERE p.product_id = :productId AND p.delete_flag =0  AND ((p.price_effective_date BETWEEN :newPriceEffectiveDate AND :newPriceTermDate OR p.price_term_date BETWEEN :newPriceEffectiveDate AND :newPriceTermDate) OR p.price_term_date IS NULL)", nativeQuery=true )
	int getCountOfPricesExistsForSamePeriod(Long productId, Date newPriceEffectiveDate,Date newPriceTermDate);
	
	@Query(value ="SELECT * FROM Prices p WHERE p.product_id = :productId AND p.delete_flag =0 AND ((p.price_effective_date BETWEEN :priceEffectiveDate AND :newPriceTermDate OR p.price_term_date BETWEEN :priceEffectiveDate AND :newPriceTermDate) OR p.price_term_date IS NULL)", nativeQuery=true )
	List<Price> findPricesOverlappingForSamePeriod(Long productId, Date priceEffectiveDate,Date newPriceTermDate);
}
