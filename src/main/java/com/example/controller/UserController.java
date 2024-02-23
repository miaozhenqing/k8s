package com.example.controller;

import com.example.config.ServerConfig;
import com.example.config.UserConfig;
import com.example.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private UserConfig userConfig;

    private String path = "/k8s/data/example.txt"; // 文件路径


    private AtomicLong currentCount = new AtomicLong();

    @GetMapping("view")
    public String viewCount() {
        Long viewCount = redisTemplate.opsForValue().increment("viewCount", 1);
        String currentShow = "<h1>redis访问次数：" + viewCount + "</h1>\n";
        String message;
        try {
            InetAddress adder = InetAddress.getLocalHost();
            message = adder.getHostName() + "-" + adder.getHostAddress();
        } catch (UnknownHostException e) {
            message = "error";
        }
        currentShow += "<h1>服务id：" + serverConfig.getServerId() + "</h1>\n";
        currentShow += "<h1>hi " + userConfig.getUserId() + "-" + userConfig.getUsername() + "</h1>\n";

        String finalStr = currentShow + "\n" + message;
        finalStr = finalStr + "\n" + "version:v13";
        logger.info(finalStr);
        return finalStr;
    }

    @GetMapping("write")
    public String write(String content) {
        if (content == null) {
            content = String.valueOf(System.currentTimeMillis());
        }
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @GetMapping("read")
    public String read() {
        StringBuilder builder = new StringBuilder("read message:\n");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @GetMapping("sleep")
    public String sleep(String minute) {

        try {
            Thread myThread = new Thread(() -> {
                long time = 60 * 1000;
                if (minute != null) {
                    time = Integer.parseInt(minute) * 60 * 1000;
                }
                logger.info("sleep start,time:{}\n", time);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info("sleep end,time:{}\n", time);
            }, "11111-thread");
            myThread.start();
        } catch (Exception e) {
            return e.toString();
        }
        return "ok:" + minute;
    }

    @GetMapping("threadTop")
    public String threadTop() {

        int count = 60;
        List<Map<Long, Long>> list = new ArrayList<>();
        while (count-- > 0) {
            Map<Long, Long> topNThreads = ThreadUtil.getTopNThreads(1000, 5);
            list.add(topNThreads);
        }
        Map<String,Long> threadNameToValueMap=new HashMap<>();
        Map<Long, Thread> threadMap = ThreadUtil.getThreadMap();
        for (Map<Long, Long> map : list) {
            logger.info("=========================\n");
            for (Map.Entry<Long, Long> entry : map.entrySet()) {
                Thread thread = threadMap.get(entry.getKey());
                logger.info("{}:{}\n", thread.getName(),entry.getValue());
            }
        }
        logger.info("threadTop...{}", list);
        return list.toString();
    }
}
