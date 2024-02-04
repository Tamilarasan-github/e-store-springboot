package com.tamilcreations.estorespringboot.users;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
	Optional<User> findByUserId(long userId);
	
	@Query(value ="SELECT * from Users u WHERE u.uuid= :userUuid AND u.delete_flag =0 ", nativeQuery =true)
	Optional<User> findUserByUserUuid(String userUuid);
	
	@Query(value ="SELECT * from Users u WHERE u.phone_number= :phoneNumber AND u.delete_flag =0 ", nativeQuery =true)
	Optional<User> findUserByPhoneNumber(String phoneNumber);
	
	@Query(value ="SELECT * from Users u WHERE u.email_id= :emailId AND u.delete_flag =0 ", nativeQuery =true)
	Optional<User> findUserByEmailId(String emailId);
	
	@Query(value ="SELECT * from Users u WHERE u.user_name= :userName AND u.delete_flag =0 ", nativeQuery =true)
	Optional<User> findUserByUserName(String userName);
}
