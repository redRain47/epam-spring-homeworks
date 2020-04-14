package com.epam.hw12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.epam.hw12.ActiveMQConfig.*;

@Slf4j
@Component
@DependsOn({"responder"})
public class Requester {
    @Autowired
    private JmsTemplate jmsTemplate;

    private Map<UUID, RequestMessage> requestMap = new HashMap<>();

    public void request(RequestMessage message) {
        requestMap.put(message.getCorrId(), message);
        log.info("Sending request: " + message);
        jmsTemplate.convertAndSend(REQUEST_QUEUE, message);
    }

    @JmsListener(destination = RESPONSE_QUEUE)
    public void receive(@Payload ResponseMessage message) {
        UUID messageCorrId = message.getCorrId();

        log.info("Receiving response: " + message);

        if (requestMap.containsKey(messageCorrId)) {
            RequestMessage requestMessage = requestMap.get(messageCorrId);
            int sumToCheck = requestMessage.getFirstNumber()
                    + requestMessage.getSecondNumber();

            if (sumToCheck != message.getResult()) {
                jmsTemplate.convertAndSend(INVALID_QUEUE, message);
            }
        } else {
            jmsTemplate.convertAndSend(INVALID_QUEUE, message);
        }
    }
}
