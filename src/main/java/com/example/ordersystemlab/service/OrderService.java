package com.example.ordersystemlab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ordersystemlab.entity.OrderRecord;
import com.example.ordersystemlab.enums.OrderSortField;
import com.example.ordersystemlab.repository.OrderRecordRepository;
import com.example.ordersystemlab.request.OrderSearchRequest;

@Service
public class OrderService {

	private final OrderRecordRepository orderRecordRepository;

	public OrderService(OrderRecordRepository orderRecordRepository) {
		this.orderRecordRepository = orderRecordRepository;
	}

	public OrderRecord createOrder(String productName, Integer quantity) {

		OrderRecord order = new OrderRecord();
		order.setProductName(productName);
		order.setQuantity(quantity);

		return orderRecordRepository.save(order);
	}

	public OrderRecord getOrder(Long id) {

		return findOrderById(id);
	}

	public Page<OrderRecord> getOrders(OrderSearchRequest request, Pageable pageable) {
		if (pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by(OrderSortField.ID.getFieldName()).descending());
		} else {
			pageable.getSort().forEach(sort -> {
				if (!OrderSortField.isAllowed(sort.getProperty())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"Invalid sort field: " + sort.getProperty());
				}
			});
		}

		return orderRecordRepository.searchOrders(request.getProductName(), request.getMinQuantity(),
				request.getMaxQuantity(), pageable);
	}

	public OrderRecord updateOrder(Long id, String productName, Integer quantity) {
		OrderRecord order = findOrderById(id);

		order.setProductName(productName);
		order.setQuantity(quantity);

		return orderRecordRepository.save(order);
	}

	public void deleteOrder(Long id) {
		OrderRecord order = findOrderById(id);
		orderRecordRepository.delete(order);
	}

	private OrderRecord findOrderById(Long id) {
		return orderRecordRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
	}
}
