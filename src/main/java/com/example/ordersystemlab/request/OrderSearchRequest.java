package com.example.ordersystemlab.request;

import jakarta.validation.constraints.Min;

public class OrderSearchRequest {

	private String productName;

	@Min(value = 1, message = "minQuantity must be at least 1")
	private Integer minQuantity;

	@Min(value = 1, message = "maxQuantity must be at least 1")
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