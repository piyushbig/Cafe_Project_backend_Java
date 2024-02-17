package com.cafe.dto;

public class ProductDetailsDto {
	private String productName;
    private String categoryName;
    private String userName;
    private double productPrice;
    private int quantity;
    
    public ProductDetailsDto(String productName,String categoryName,  double productPrice, int quantity, String cafeName) {
        this.productName=productName;
        this.categoryName=categoryName;
        this.productPrice=productPrice;
        this. quantity= quantity;
        this.userName=cafeName;
    }

    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCafeName() {
		return userName;
	}
	public void setCafeName(String userName) {
		this.userName = userName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    


}
