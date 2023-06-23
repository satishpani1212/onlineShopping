package com.shoppingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.shoppingapp.model.Product;

public interface ProductRepository  extends MongoRepository<Product, String>{

	@Query(value="{productName:'?0'}")
	Product findByProductName(String productName);
}
