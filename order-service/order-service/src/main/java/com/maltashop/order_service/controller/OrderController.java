package com.maltashop.order_service.controller;

import com.maltashop.order_service.dto.OrderRequest;
import com.maltashop.order_service.entity.Order;
import com.maltashop.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody OrderRequest request) {

        Order order = orderService.createOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }
}