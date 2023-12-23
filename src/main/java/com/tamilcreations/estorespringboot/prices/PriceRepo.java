package com.tamilcreations.estorespringboot.prices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepo extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price>
{
	@Query(value="SELECT * FROM Prices p WHERE p.product_id = :productId  AND (price_effective_date <= :currentDateAndTime AND price_term_date >= :currentDateAndTime) OR  (price_effective_date <= :currentDateAndTime AND price_term_date = NULL)", nativeQuery=true)
	List<Price> findPriceByProductId(long productId, String currentDateAndTime);
}
