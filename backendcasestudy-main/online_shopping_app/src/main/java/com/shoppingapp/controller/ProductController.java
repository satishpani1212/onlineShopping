package com.shoppingapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingapp.exception.EntitiesDoesntMatchException;
import com.shoppingapp.exception.EntityAlreadyExistException;
import com.shoppingapp.exception.EntityCannotBeCreated;
import com.shoppingapp.exception.ProductNotFoundException;
import com.shoppingapp.model.Product;
import com.shoppingapp.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/v1.0/shopping/all/")
	public List<Product> allProducts() throws Exception {
		log.info("inside Product Controller searchall");
		try {
			return productService.getAllProducts();
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/v1.0/shopping/products/search/{productname}")
	public Product searchbyProduct(@PathVariable String productname) throws Exception {
		log.info("inside Product Controller search by product");
		try {
			
			Product product = productService.searchByProductName(productname);
			return product;
			
		}catch(Exception e){
			
			if(e.getMessage()=="Product Not found")
				throw new ProductNotFoundException(e.getMessage());
			throw e;
		}
		
	}
	
	@PostMapping("/v1.0/shopping/add/")
	public Product addProduct(@RequestBody Product product) throws Exception {
		log.info("inside Product Controller addProduct");
		try {
			
			Product newProduct=productService.createNewProduct(product);
			return newProduct;
			
		}catch(Exception e){
			
			if(e.getMessage()=="Product already Exists") {
				throw new EntityAlreadyExistException("Product already Exists");
			}
			else if(e.getMessage()=="Cannot create the Product") {
				throw new EntityCannotBeCreated("Cannot create the Product");
			}
			throw new Exception(e.getMessage());
		}
		
	}
	
	@PutMapping("/v1.0/shopping/{productname}/update/")
	public Product updateProductById(@RequestBody Product product, @PathVariable String productname) throws Exception {
		log.info("inside Product Controller update product ");
		try {
			
			Product updatedProduct=productService.updateProductStatus(product,productname);
			return updatedProduct;
			
		}catch(Exception e){
			if(e.getMessage()=="Product doesnot Exists") {
				throw new EntitiesDoesntMatchException("Product doesnot Exists");
			}
			throw new Exception(e.getMessage());
		}

		
	}
	
	@DeleteMapping("/v1.0/shopping/{productname}/delete/")
	public ResponseEntity<String> deleteProductById(@PathVariable String productname) {
		log.info("inside Product Controller delete product");
		try {
			
			productService.deleteProduct(productname);
			return new ResponseEntity<String>("Product deleted Sucessfully", HttpStatus.OK);
			
		}catch(Exception e){
			if(e.getMessage()=="Product doesnot Exists") {
				return new ResponseEntity<String>("Product doesnot Exists", HttpStatus.ALREADY_REPORTED);
			}
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
