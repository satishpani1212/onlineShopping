package com.shoppingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoppingapp.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

//	 @Query(fields="{'firstname' : 1, 'countrycode' : 1, 'id' : 1}")
//	 public List<User> findAllUsers();

	 @Query(value="{loginId:'?0'}")
	 public User findByUserName(String loginId);

	 @Query(value="{emailID:'?0'}")
	 public User findByUserEmail(String email);
}
