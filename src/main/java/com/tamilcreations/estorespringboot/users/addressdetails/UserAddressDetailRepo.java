package com.tamilcreations.estorespringboot.users.addressdetails;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserAddressDetailRepo extends JpaRepository<UserAddressDetail, Long>, JpaSpecificationExecutor<UserAddressDetail>
{
	@Query(value="SELECT * FROM UserAddressDetails u WHERE u.user_id = :userId AND u.delete_flag =0 ", nativeQuery=true)
	List<UserAddressDetail> findByUserId(long userId);
	
	@Query(value="SELECT * FROM UserAddressDetails u WHERE u.uuid = :userAddressUuid AND u.delete_flag =0 ", nativeQuery=true)
	List<UserAddressDetail> findUserAddressDetailsByUserAddressUuid(String userAddressUuid);
}
