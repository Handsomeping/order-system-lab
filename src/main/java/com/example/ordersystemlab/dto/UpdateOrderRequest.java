package com.example.ordersystemlab.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateOrderRequest {

	@NotBlank(message = "productName is required")
	private String productName;

	@NotNull(message = "quantity is required")
	@Min(value = 1, message = "quantity must be at least 1")
	private Integer quantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
