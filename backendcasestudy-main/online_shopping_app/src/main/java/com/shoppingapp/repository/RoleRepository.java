package com.shoppingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shoppingapp.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	@Query(value="{loginId:'?0'}")
	Role findByLoginId(String loginId);

	
}
