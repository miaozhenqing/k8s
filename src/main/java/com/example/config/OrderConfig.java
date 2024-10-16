package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Value("${orderId}")
    public int orderId;
    @Value("${orderName}")
    public String orderName;
    @Value("${color}")
    public String color;
    @Value("${memory}")
    public int memory;
}
