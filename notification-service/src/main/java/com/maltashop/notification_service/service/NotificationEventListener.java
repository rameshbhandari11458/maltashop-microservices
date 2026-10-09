
package com.maltashop.notification_service.service;

import com.maltashop.notification_service.config.RabbitMQConfig;
import com.maltashop.notification_service.entity.Notification;
import com.maltashop.notification_service.event.OrderPlacedEvent;
import com.maltashop.notification_service.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    private final NotificationRepository notificationRepository;

    public NotificationEventListener(
            NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void handleOrderPlaced(OrderPlacedEvent event) {

        System.out.println(
                "Received OrderPlaced event for order: "
                        + event.getOrderId()
                        + ", correlationId: "
                        + event.getCorrelationId()
        );

        Notification notification = new Notification(
                event.getOrderId(),
                event.getCustomerId(),
                "Order " + event.getOrderId()
                        + " has been placed successfully.",
                "SENT"
        );

        notificationRepository.save(notification);

        System.out.println(
                "Notification saved for order: "
                        + event.getOrderId()
                        + ", correlationId: "
                        + event.getCorrelationId()
        );
    }
}
