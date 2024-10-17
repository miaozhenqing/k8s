package com.example.controller;

import com.example.config.OrderConfig;
import com.example.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController()
@RequestMapping("/order")
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
            builder.append(adder.getHostName() + "-" + adder.getHostAddress()).append("</br>");
        } catch (UnknownHostException e) {
            builder.append("error");
        }
        builder.append("serverId:").append(serverConfig.getServerId()).append("</br>");
        builder.append("orderId:").append(orderConfig.orderId).append("</br>");
        builder.append("orderName:").append(orderConfig.orderName).append("</br>");
        builder.append("orderPrice:").append(orderConfig.price).append("</br>");
        String string = builder.toString();
        logger.info(string);
        return string;
    }
}
