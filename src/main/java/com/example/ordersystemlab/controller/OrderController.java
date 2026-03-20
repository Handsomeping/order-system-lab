package com.example.ordersystemlab.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ordersystemlab.dto.CreateOrderRequest;
import com.example.ordersystemlab.entity.OrderRecord;
import com.example.ordersystemlab.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderRecord createOrder(@RequestBody CreateOrderRequest request) {

        return orderService.createOrder(
                request.getProductName(),
                request.getQuantity()
        );
    }

    @GetMapping("/{id}")
    public OrderRecord getOrder(@PathVariable Long id) {

        return orderService.getOrder(id);
    }
    
    @GetMapping
    public Page<OrderRecord> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}
