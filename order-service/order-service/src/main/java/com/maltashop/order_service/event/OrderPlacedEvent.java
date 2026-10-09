package com.maltashop.order_service.event;

public class OrderPlacedEvent {

    private Long eventId;

    private Long orderId;

    private Long customerId;

    private Long productId;

    private Integer quantity;


    public OrderPlacedEvent() {
    }


    public OrderPlacedEvent(
            Long eventId,
            Long orderId,
            Long customerId,
            Long productId,
            Integer quantity) {

        this.eventId = eventId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
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