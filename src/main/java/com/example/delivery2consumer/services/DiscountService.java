package com.example.delivery2consumer.services;

import com.example.delivery2consumer.enums.Status;
import com.example.delivery2consumer.dto.OrderStatusUpdateMessage;
import com.example.delivery2consumer.models.Order;
import com.example.delivery2consumer.models.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class DiscountService {

    private final RestTemplate restTemplate;

    @Autowired
    public DiscountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void checkDiscount(Map<UUID, OrderStatusUpdateMessage> orderStatusMap, UUID orderId, String newStatus, LocalDateTime messageTime) {
        if (orderStatusMap.containsKey(orderId)) {
            OrderStatusUpdateMessage existingMessage = orderStatusMap.get(orderId);
            Duration duration = Duration.between(existingMessage.getDate(), messageTime);

            if (existingMessage.getStatus().equals(Status.IN_TRANSIT.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе IN_TRANSIT более 20 минут.");
                applyDiscount(orderId, 0.2);
            } else if (existingMessage.getStatus().equals(Status.ASSEMBLING.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе ASSEMBLING более 10 минут.");
                applyDiscount(orderId, 0.1);
            } else if (existingMessage.getStatus().equals(Status.ARRIVED.name()) && duration.toSeconds() > 1) {
                System.out.println("Заказ ID: " + orderId + " был в статусе ARRIVED более 5 минут.");
                applyDiscount(orderId, 0.05);
            }
            existingMessage.setStatus(newStatus);
            existingMessage.setDate(messageTime);
        } else {
            orderStatusMap.put(orderId, new OrderStatusUpdateMessage(orderId, newStatus, messageTime));
            System.out.println("Сообщение из очереди: Заказ ID: " + orderId + ", Текущий статус: " + newStatus + " Время: " + messageTime);
        }
    }

    private ResponseDto applyDiscount(UUID id, double discount) {
        String url = "http://localhost:8081/api/orders/discount/" + id + "/" + discount;
        try {
            ResponseEntity<ResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, HttpEntity.EMPTY, ResponseDto.class);
            ResponseDto response = responseEntity.getBody();
            if (response != null) {
                System.out.println("Скидка в " + (discount * 100) + "% применена для заказа с ID: " + id);
                System.out.println("Старая цена: " + response.getOldPrice() + ", Новая цена: " + response.getNewPrice());
                return response;
            } else {
                System.out.println("Не удалось получить данные по заказу с ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при применении скидки для заказа с ID: " + id);
            e.printStackTrace();
        }
        return null;
    }

}
