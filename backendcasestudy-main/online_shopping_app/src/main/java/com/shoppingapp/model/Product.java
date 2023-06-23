package com.shoppingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="productData")
public class Product {

	@Transient
    public static final String SEQUENCE_NAME = "products_sequence";
	
	@Id
	private long productId;
	private String productName;
	private String productDesc;
	private String features;
	private String status;
	private long price;
	
	public Product() {
		
	}
	
	public Product(long productId, String productName, String productDesc, String features, String status, long price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.features = features;
		this.status = status;
		this.price = price;
	}
	
	
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", features=" + features + ", status=" + status + ", price=" + price + "]";
	}
	
	
	
}
