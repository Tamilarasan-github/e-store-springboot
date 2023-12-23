package com.tamilcreations.estorespringboot.discounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepo extends JpaRepository<Discount, Long>, JpaSpecificationExecutor<Discount>
{
	@Query(value="SELECT * FROM Discounts d WHERE d.product_id = :productId AND (discount_effective_date <= :currentDateAndTime AND discount_term_date >= :currentDateAndTime) OR (discount_effective_date <= :currentDateAndTime AND discount_term_date >= NULL)", nativeQuery=true)
	List<Discount> findDiscountsByProductId(long productId, String currentDateAndTime);
}
