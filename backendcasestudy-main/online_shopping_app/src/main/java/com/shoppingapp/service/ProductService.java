package com.shoppingapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingapp.exception.EntityNotFoundException;
import com.shoppingapp.model.Product;
import com.shoppingapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public List<Product> getAllProducts() {
		log.info("Inside Product Service getall");
		return productRepository.findAll();
		
	}

	public Product searchByProductName(String productname) throws Exception {
		log.info("Inside Product Service by product name"+productname);
		Product product = productRepository.findByProductName(productname);
		
		if(product!=null) {
			log.info("log.info(product.toString());");
			return product;
		}
		 
		throw new Exception("Product Not found");
		
	}

	public Product createNewProduct(Product product) throws Exception {
		log.info("Inside Product Service create new product");
		Product newProduct= new Product();
		Product existingProductCheck= productRepository.findByProductName(product.getProductName());
		if(existingProductCheck!=null) {
			throw new Exception("Product already Exists");
		}
		try {
			newProduct.setFeatures(product.getFeatures());
			newProduct.setPrice(product.getPrice());
			newProduct.setProductDesc(product.getProductDesc());
			newProduct.setProductName(product.getProductName());
			newProduct.setStatus(product.getStatus());
			newProduct.setProductId(sequenceGenerator.generateSequence(Product.SEQUENCE_NAME));
			productRepository.save(newProduct);
			return newProduct;
		}catch (Exception e) {
			throw new Exception("Cannot create the Product");
		}
		
	}

	public Product updateProductStatus(Product product, String productname) throws Exception {
		log.info("Inside Product Service update product status");
		Product existingProductCheck= productRepository.findByProductName(product.getProductName());
		if(existingProductCheck==null) {
			throw new Exception("Product doesnot Exists");
		}
		existingProductCheck.setStatus(product.getStatus());
		return productRepository.save(existingProductCheck);
		
	}

	public void deleteProduct(String productname) throws Exception {
		log.info("Inside Product Service delete product");
		Product existingProductCheck= productRepository.findByProductName(productname);
		if(existingProductCheck==null) {
			throw new Exception("Product doesnot Exists");
		}
		productRepository.delete(existingProductCheck);
		
		
	}
	
}
