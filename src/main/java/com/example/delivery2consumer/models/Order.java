package com.example.delivery2consumer.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order{
    private UUID id;
    private LocalDateTime orderDate;
    private String customerName;
    private String customerAddress;
    private double orderCost;
    private String deliveryInstructions;
    private LocalDateTime deliveryDate;

    public Order(UUID id, LocalDateTime orderDate, String customerName, String customerAddress, double orderCost, String deliveryInstructions, LocalDateTime deliveryDate) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderCost = orderCost;
        this.deliveryInstructions = deliveryInstructions;
        this.deliveryDate = deliveryDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
