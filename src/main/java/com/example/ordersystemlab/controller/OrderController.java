package com.example.ordersystemlab.controller;

import org.springframework.web.bind.annotation.*;

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
}
