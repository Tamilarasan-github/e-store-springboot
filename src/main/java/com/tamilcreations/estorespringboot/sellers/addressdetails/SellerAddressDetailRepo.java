package com.tamilcreations.estorespringboot.sellers.addressdetails;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SellerAddressDetailRepo extends JpaRepository<SellerAddressDetail, Long>, JpaSpecificationExecutor<SellerAddressDetail>
{
	@Query(value="SELECT * FROM SellerAddressDetails s WHERE s.seller_id = :sellerId", nativeQuery=true)
	List<SellerAddressDetail> findBySellerId(long sellerId);
}
