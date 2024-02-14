package com.cafe.dto;

import com.cafe.entity.Role;

public class CustomerLoginStatus extends Status{

	private int customerId;
	private String name;
	private Role role;
	
	/*private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role2) {
		this.role = role2;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
