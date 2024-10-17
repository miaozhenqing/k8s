package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Value("${order.id}")
    public int orderId;
    @Value("${order.name}")
    public String orderName;
    @Value("${order.price}")
    public int price;
}
