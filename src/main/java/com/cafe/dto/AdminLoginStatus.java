package com.cafe.dto;

import org.springframework.stereotype.Component;

import com.cafe.entity.Role;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AdminLoginStatus extends Status{

	private int adminId;
	private String name;
	private Role role;
	
	/*private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role2) {
		this.role = role2;
	}
	public int getCustomerId() {
		return adminId;
	}
	public void setCustomerId(int customerId) {
		this.adminId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
