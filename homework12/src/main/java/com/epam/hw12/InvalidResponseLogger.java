package com.epam.hw12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.epam.hw12.ActiveMQConfig.INVALID_QUEUE;

@Slf4j
@Component
public class InvalidResponseLogger {
    @JmsListener(destination = INVALID_QUEUE)
    public void handleInvalidResponse(@Payload ResponseMessage message) {
        log.warn("Invalid response: " + message);
    }
}
