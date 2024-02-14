package com.cafe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="cart")
public class cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartfoodId;
	
	@Column(name="food_name")
	private String foodName;
	
	private String cafeName;
	
	private String Category;
	
	private int price;
	
	private int quantity;

	public int getCartfoodId() {
		return cartfoodId;
	}

	public void setCartfoodId(int cartfoodId) {
		this.cartfoodId = cartfoodId;
	}

	public String getFoodName() {
		return foodName;
	}
 
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getCafeName() {
		return cafeName;
	}

	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}
