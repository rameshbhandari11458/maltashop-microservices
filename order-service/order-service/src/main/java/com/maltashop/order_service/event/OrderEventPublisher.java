package com.maltashop.order_service.event;

import com.maltashop.order_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;


    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishOrderPlaced(OrderPlacedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_ROUTING_KEY,
                event
        );

        System.out.println(
                "OrderPlaced event published for order: "
                        + event.getOrderId()
        );
    }
}