package com.epam.springcloud.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableEurekaClient
@SpringBootApplication
public class NotificationServer {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServer.class, args);
    }
}
