package com.example.ordersystemlab.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;

public class OrderSearchRequest {

	private String productName;

	@Min(value = 1, message = "minQuantity must be at least 1")
	private Integer minQuantity;

	@Min(value = 1, message = "maxQuantity must be at least 1")
	private Integer maxQuantity;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdFrom;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdTo;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public LocalDateTime getCreatedFrom() {
		return createdFrom;
	}

	public void setCreatedFrom(LocalDateTime createdFrom) {
		this.createdFrom = createdFrom;
	}

	public LocalDateTime getCreatedTo() {
		return createdTo;
	}

	public void setCreatedTo(LocalDateTime createdTo) {
		this.createdTo = createdTo;
	}

}