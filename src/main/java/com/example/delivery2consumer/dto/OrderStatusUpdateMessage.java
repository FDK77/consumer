package com.example.delivery2consumer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderStatusUpdateMessage {
    @JsonProperty("orderId")
    private UUID orderId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    public OrderStatusUpdateMessage() {}

    public OrderStatusUpdateMessage(UUID orderId, String status, LocalDateTime date) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
       this.status = status;
    }
}
