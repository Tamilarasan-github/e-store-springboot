package com.tamilcreations.estorespringboot.userRoles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepo extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole>
{
	Optional<UserRole> findByRoleId(long roleId);
	
	@Query(value ="SELECT * from UserRoles u WHERE u.user_id= :userId ", nativeQuery =true)
	Optional<UserRole> findUserRoleByUserId(Long userId);
	
	@Query(value ="SELECT u.role_id from UserRoles u WHERE u.user_id= :userId ", nativeQuery =true)
	Optional<Long> findRoleIdByUserId(Long userId);
}
