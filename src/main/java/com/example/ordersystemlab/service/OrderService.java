package com.example.ordersystemlab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ordersystemlab.entity.OrderRecord;
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
        return orderRecordRepository.findAll(pageable);
    }
}
