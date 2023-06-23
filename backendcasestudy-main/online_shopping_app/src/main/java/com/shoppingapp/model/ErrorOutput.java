package com.shoppingapp.model;

import java.io.Serializable;

public class ErrorOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;
	private String description;
	
	public int getStatuscode() {
		return statusCode;
	}
	public void setStatuscode(int statuscode) {
		this.statusCode = statuscode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ErrorOutput [statusCode=" + statusCode + ", description=" + description + "]";
	}
	
	
}
