package com.example.ordersystemlab.service;

import java.util.List;

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

        return orderRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public Page<OrderRecord> getOrders(Pageable pageable) {
    	if (pageable.getSort().isUnsorted()) {
    		pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(OrderSortField.ID.getFieldName()).descending()
            );
    	} 
    	else {
    		List<Sort.Order> sortList = pageable.getSort().toList();
    		sortList.forEach( (sort) -> { 
    			if (!OrderSortField.isAllowed(sort.getProperty())) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Invalid sort field: " + sort.getProperty()
                    );
                }
    		});
    	}
    	
        return orderRecordRepository.findAll(pageable);
    }
    
    public OrderRecord updateOrder(Long id, String productName, Integer quantity) {
        OrderRecord order = orderRecordRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found: " + id
                ));

        order.setProductName(productName);
        order.setQuantity(quantity);

        return orderRecordRepository.save(order);
    }
}
