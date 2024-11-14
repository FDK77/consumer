package com.example.delivery2consumer.rabbitListener;

import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import com.example.delivery2consumer.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderStatusListener {

    private final DiscountService discountService;
    private final Map<UUID, OrderStatusUpdateMessage> orderStatusMap;

    @Autowired
    public OrderStatusListener(DiscountService discountService, Map<UUID, OrderStatusUpdateMessage> orderStatusMap) {
        this.discountService = discountService;
        this.orderStatusMap = orderStatusMap;
    }

    @RabbitListener(queues = "firstQueue")
    public void listen(OrderStatusUpdateMessage message) {
        UUID orderId = message.getOrderId();
        String newStatus = message.getStatus();
        LocalDateTime messageTime = message.getDate();
        discountService.checkDiscount(orderStatusMap, orderId, newStatus, messageTime);
    }
}
