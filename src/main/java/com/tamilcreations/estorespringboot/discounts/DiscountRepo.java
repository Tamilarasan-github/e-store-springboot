package com.tamilcreations.estorespringboot.discounts;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;



public interface DiscountRepo extends JpaRepository<Discount, Long>, JpaSpecificationExecutor<Discount>
{
//	@Query(value="SELECT * FROM Discounts d WHERE d.product_id = :productId AND (discount_effective_date <= :currentDateAndTime AND discount_term_date >= :currentDateAndTime) OR (discount_effective_date <= :currentDateAndTime AND discount_term_date >= NULL)", nativeQuery=true)
//	List<Discount> findDiscountsByProductId(long productId, String currentDateAndTime);
//	
	@Query(value="SELECT * FROM Discounts p WHERE p.uuid = :discountUuid ", nativeQuery=true)
	Optional<Discount> findDiscountByDiscountUuid(String discountUuid);
			
	@Query(value="SELECT * FROM Discounts p WHERE p.product_id = :productId  AND (discount_effective_date <= :currentDateAndTime AND discount_term_date >= :currentDateAndTime) OR  (discount_effective_date <= :currentDateAndTime AND discount_term_date = NULL)", nativeQuery=true)
	Optional<Discount> findDiscountByProductId(long productId, String currentDateAndTime);
	
	Optional<Discount> findByUuid(String uuid);
	
	@Query(value="SELECT * FROM Discounts p WHERE p.product_id = :productId", nativeQuery=true)
	List<Discount> findDiscountsByProductId(Long productId);
	
	@Query(value ="SELECT count(*) FROM Discounts p WHERE p.product_id = :productId AND ((p.discount_effective_date BETWEEN :newDiscountEffectiveDate AND :newDiscountTermDate OR p.discount_term_date BETWEEN :newDiscountEffectiveDate AND :newDiscountTermDate) OR p.discount_term_date IS NULL)", nativeQuery=true )
	int getCountOfDiscountsExistsForSamePeriod(Long productId, Date newDiscountEffectiveDate,Date newDiscountTermDate);
	
	@Query(value ="SELECT * FROM Discounts p WHERE p.product_id = :productId AND ((p.discount_effective_date BETWEEN :discountEffectiveDate AND :newDiscountTermDate OR p.discount_term_date BETWEEN :discountEffectiveDate AND :newDiscountTermDate) OR p.discount_term_date IS NULL)", nativeQuery=true )
	List<Discount> findDiscountsOverlappingForSamePeriod(Long productId, Date discountEffectiveDate,Date newDiscountTermDate);
}
