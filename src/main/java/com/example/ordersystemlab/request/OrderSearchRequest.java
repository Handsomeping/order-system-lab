package com.example.ordersystemlab.request;

public class OrderSearchRequest {

	private String productName;

	private Integer minQuantity;

	private Integer maxQuantity;

	public String getProductName() {
		return productName;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
}