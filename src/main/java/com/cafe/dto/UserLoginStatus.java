package com.cafe.dto;

import com.cafe.entity.Role;

public class UserLoginStatus extends Status {
	
	private int userId;
	
	private String name;
	
	private Role role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

//	public void setRole(Role role) {
//		this.role = role;
//	}
	
	
	

}
