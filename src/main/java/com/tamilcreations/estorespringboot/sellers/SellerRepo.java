package com.tamilcreations.estorespringboot.sellers;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepo extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller>
{
	
	Seller findBySellerId(long sellerId);
	
	 @Query(value ="SELECT s FROM Sellers s WHERE s.uuid :sellerUuid ", nativeQuery =true)
	 Optional<Seller> findSellerBySellerUuid(@Param("sellerUuid") String sellerUuid);
	 
	 @Query(value ="SELECT seller_id FROM Sellers s WHERE s.uuid :sellerUuid ", nativeQuery =true)
	 Optional<Long> findSellerIdBySellerUuid(@Param("sellerUuid") String sellerUuid);
	
	 @Query(value ="SELECT s FROM Sellers s WHERE s.sellerId > :after ORDER BY s.sellerId ASC LIMIT :limit", nativeQuery =true)
	 List<Seller> findSellers(@Param("limit") int limit, @Param("after") Long after);
	 
	 @Query(value ="SELECT s FROM Sellers s ORDER BY s.sellerId ASC LIMIT :limit", nativeQuery =true)
	 List<Seller> findSellers(@Param("limit") int limit);
}
