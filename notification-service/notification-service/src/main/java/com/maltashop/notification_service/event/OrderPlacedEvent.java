package com.maltashop.notification_service.event;

public class OrderPlacedEvent {

    private Long eventId;
    private Long orderId;
    private Long customerId;
    private Long productId;
    private Integer quantity;

    public OrderPlacedEvent() {
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}