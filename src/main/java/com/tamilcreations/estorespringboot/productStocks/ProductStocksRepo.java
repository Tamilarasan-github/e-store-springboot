package com.tamilcreations.estorespringboot.productStocks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProductStocksRepo extends JpaRepository<ProductStocks, Long>, JpaSpecificationExecutor<ProductStocks>
{
//	@Query(value="SELECT * FROM ProductStocks d WHERE d.product_id = :productId AND (stocks_effective_date <= :currentDateAndTime AND stocks_term_date >= :currentDateAndTime) OR (stocks_effective_date <= :currentDateAndTime AND stocks_term_date >= NULL)", nativeQuery=true)
//	List<ProductStocks> findProductStocksByProductId(long productId, String currentDateAndTime);
//	
	@Query(value="SELECT * FROM ProductStocks p WHERE p.uuid = :productStocksUuid AND p.delete_flag =0 ", nativeQuery=true)
	Optional<ProductStocks> findProductStocksByProductStocksUuid(String productStocksUuid);
			
	@Query(value="SELECT * FROM ProductStocks p WHERE p.product_id = :productId AND p.delete_flag =0  AND (stocks_effective_date <= :currentDateAndTime AND stocks_term_date >= :currentDateAndTime) OR  (stocks_effective_date <= :currentDateAndTime AND stocks_term_date = NULL)", nativeQuery=true)
	Optional<ProductStocks> findProductStocksByProductId(long productId, String currentDateAndTime);
	
	Optional<ProductStocks> findByUuid(String uuid);
	
	@Query(value="SELECT * FROM ProductStocks p WHERE CASE WHEN :after IS NOT NULL THEN stock_id > :after AND product_id =:productId AND p.delete_flag =0 WHEN :before IS NOT NULL THEN stock_id < :before AND product_id =:productId AND p.delete_flag =0 ELSE product_id =:productId AND p.delete_flag =0 END ORDER BY stock_id ASC LIMIT :limit ", nativeQuery=true)
	List<ProductStocks> findProductStocksByProductId(@Param("productId") Long productId, @Param("limit") int limit, @Param("after") Long after, @Param("before") Long before);
	
	@Query(value="SELECT * FROM ProductStocks p WHERE p.product_id = :productId AND p.delete_flag =0 ", nativeQuery=true)
	List<ProductStocks> findProductStocksByProductId(Long productId);
	
	@Query(value ="SELECT count(*) FROM ProductStocks p WHERE p.product_id = :productId AND p.delete_flag =0 AND ((p.stocks_effective_date BETWEEN :newProductStocksEffectiveDate AND :newProductStocksTermDate OR p.stocks_term_date BETWEEN :newProductStocksEffectiveDate AND :newProductStocksTermDate) OR p.stocks_term_date IS NULL)", nativeQuery=true )
	int getCountOfProductStocksExistsForSamePeriod(Long productId, Date newProductStocksEffectiveDate,Date newProductStocksTermDate);
	
	@Query(value ="SELECT * FROM ProductStocks p WHERE p.product_id = :productId AND p.delete_flag =0 AND ((p.stocks_effective_date BETWEEN :productStocksEffectiveDate AND :newProductStocksTermDate OR p.stocks_term_date BETWEEN :productStocksEffectiveDate AND :newProductStocksTermDate) OR p.stocks_term_date IS NULL)", nativeQuery=true )
	List<ProductStocks> findProductStocksOverlappingForSamePeriod(Long productId, Date productStocksEffectiveDate,Date newProductStocksTermDate);
}
