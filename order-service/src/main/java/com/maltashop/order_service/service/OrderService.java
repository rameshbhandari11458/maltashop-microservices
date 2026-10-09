
package com.maltashop.order_service.service;

import com.maltashop.order_service.dto.OrderRequest;
import com.maltashop.order_service.entity.Order;
import com.maltashop.order_service.event.OrderEventPublisher;
import com.maltashop.order_service.event.OrderPlacedEvent;
import com.maltashop.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderService(
            OrderRepository orderRepository,
            OrderEventPublisher eventPublisher) {

        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order createOrder(OrderRequest request) {

        Order order = new Order(
                request.getCustomerId(),
                request.getProductId(),
                request.getQuantity(),
                request.getTotalAmount(),
                "PLACED"
        );

        Order savedOrder = orderRepository.save(order);

        String correlationId = UUID.randomUUID().toString();

        OrderPlacedEvent event = new OrderPlacedEvent(
                savedOrder.getId(),
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                correlationId
        );

        eventPublisher.publishOrderPlaced(event);

        return savedOrder;
    }
}
