package com.example.controller;

import com.example.config.OrderConfig;
import com.example.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private OrderConfig orderConfig;

    @GetMapping("/getDefault")//http://localhost:30000/order/getDefault
    public String getDefault() {
        StringBuilder builder = new StringBuilder();
        try {
            InetAddress adder = InetAddress.getLocalHost();
            builder.append(adder.getHostName() + "-" + adder.getHostAddress()).append("\n");
        } catch (UnknownHostException e) {
            builder.append("error");
        }
        builder.append("serverId:").append(serverConfig.getServerId()).append("\n");
        builder.append("orderId:").append(orderConfig.orderId).append("\n");
        builder.append("orderName:").append(orderConfig.orderName).append("\n");
        builder.append("color:").append(orderConfig.color).append("\n");
        builder.append("memory:").append(orderConfig.memory).append("\n");
        String string = builder.toString();
        logger.info(string);
        return string;
    }
}
