package com.tamilcreations.estorespringboot.sellers;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepo extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller>
{
	
	Seller findBySellerId(long sellerId);
	
	
	 @Query("SELECT s FROM Seller s WHERE s.sellerId > :after ORDER BY s.sellerId ASC LIMIT :limit")
	 List<Seller> findSellers(@Param("limit") int limit, @Param("after") Long after);
	 
	 @Query("SELECT s FROM Seller s ORDER BY s.sellerId ASC LIMIT :limit")
	 List<Seller> findSellers(@Param("limit") int limit);
}
