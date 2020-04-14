package com.epam.hw12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.config.JmsListenerEndpointRegistry;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Requester requester = ctx.getBean(Requester.class);
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            requester.request(new RequestMessage(
                    UUID.randomUUID(),
                    random.nextInt(10),
                    random.nextInt(10)
            ));
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------------");
        System.out.println("FINISHING");
        System.out.println("---------------------------------------");
        ctx.getBean(JmsListenerEndpointRegistry.class).stop();
        ctx.close();
    }
}
