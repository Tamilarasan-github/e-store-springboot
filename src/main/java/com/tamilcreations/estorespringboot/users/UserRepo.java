package com.tamilcreations.estorespringboot.users;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
	Optional<User> findByUserId(long userId);
	
	@Query(value ="SELECT * from Users u WHERE u.uuid= :userUuid ", nativeQuery =true)
	Optional<User> findUserByUserUuid(String userUuid);
}
