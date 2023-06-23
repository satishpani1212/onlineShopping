package com.shoppingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userData")
public class User {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;
	private String loginId;	
	private String firstName;
	private String lastName;
	private String emailID;
	private String password;
	private long phoneNumber;
	
	public User() {
		
	}
	
	
	public User(long id, String loginId, String firstName, String lastName, String emailID, String password,
			long phoneNumber) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailID=" + emailID + ", password=" + password + ", phoneNumber=" + phoneNumber + "]";
	}		
	
}
