package com.shoppingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userRole")
public class Role {

	@Transient
    public static final String SEQUENCE_NAME = "userrole_sequence";
	@Id
	private long roleId;
	private String loginId;
	private String role;
	
	public Role() {
		
	}
	public Role(long roleId, String loginId, String role) {
		super();
		this.roleId = roleId;
		this.loginId = loginId;
		this.role = role;
	}
	
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", loginId=" + loginId + ", role=" + role + "]";
	}
	
	
	
}
