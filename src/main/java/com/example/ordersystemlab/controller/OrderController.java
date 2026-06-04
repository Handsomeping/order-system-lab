package com.example.ordersystemlab.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ordersystemlab.dto.CreateOrderRequest;
import com.example.ordersystemlab.dto.UpdateOrderRequest;
import com.example.ordersystemlab.entity.OrderRecord;
import com.example.ordersystemlab.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public OrderRecord createOrder(@Valid @RequestBody CreateOrderRequest request) {

		return orderService.createOrder(request.getProductName(), request.getQuantity());
	}

	@GetMapping("/{id}")
	public OrderRecord getOrder(@PathVariable Long id) {

		return orderService.getOrder(id);
	}

	@GetMapping
	public Page<OrderRecord> getOrders(Pageable pageable) {
		return orderService.getOrders(pageable);
	}

	@PutMapping("/{id}")
	public OrderRecord updateOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderRequest request) {
		return orderService.updateOrder(id, request.getProductName(), request.getQuantity());
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}
