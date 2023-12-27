package com.tamilcreations.estorespringboot.prices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepo extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price>
{
	@Query(value="SELECT * FROM Prices p WHERE p.uuid = :priceUuid ", nativeQuery=true)
	Optional<Price> findPriceByPriceUuid(String priceUuid);
			
	@Query(value="SELECT * FROM Prices p WHERE p.product_id = :productId  AND (price_effective_date <= :currentDateAndTime AND price_term_date >= :currentDateAndTime) OR  (price_effective_date <= :currentDateAndTime AND price_term_date = NULL)", nativeQuery=true)
	Optional<Price> findPriceByProductId(long productId, String currentDateAndTime);
	
	Optional<Price> findByUuid(String uuid);
	
	@Query(value="SELECT * FROM Prices p WHERE p.product_id = :productId", nativeQuery=true)
	List<Price> findPricesByProductId(Long productId);
	
	@Query(value ="SELECT count(*) FROM Prices p WHERE p.product_id = :productId AND ((p.price_effective_date BETWEEN :newPriceEffectiveDate AND :newPriceTermDate OR p.price_term_date BETWEEN :newPriceEffectiveDate AND :newPriceTermDate) OR p.price_term_date IS NULL)", nativeQuery=true )
	int getCountOfPricesExistsForSamePeriod(Long productId, Date newPriceEffectiveDate,Date newPriceTermDate);
	
	@Query(value ="SELECT * FROM Prices p WHERE p.product_id = :productId AND ((p.price_effective_date BETWEEN :priceEffectiveDate AND :newPriceTermDate OR p.price_term_date BETWEEN :priceEffectiveDate AND :newPriceTermDate) OR p.price_term_date IS NULL)", nativeQuery=true )
	List<Price> findPricesOverlappingForSamePeriod(Long productId, Date priceEffectiveDate,Date newPriceTermDate);
}
