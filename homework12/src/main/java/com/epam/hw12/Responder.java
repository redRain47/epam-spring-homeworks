package com.epam.hw12;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.hw12.ActiveMQConfig.REQUEST_QUEUE;
import static com.epam.hw12.ActiveMQConfig.RESPONSE_QUEUE;

@Slf4j
@Component
public class Responder {
    @Autowired
    private JmsTemplate jmsTemplate;

    private List<RequestMessage> requestList = new ArrayList<>();

    @JmsListener(destination = REQUEST_QUEUE)
    public void receiveAndResponse(@Payload RequestMessage message) {
        if (requestList.size() < 10) {
            log.info("Receiving request: " + message);
            requestList.add(message);
        } else {
            List<ResponseMessage> sums = requestList.stream()
                    .map(msg -> new ResponseMessage(message.getCorrId(),
                            msg.getFirstNumber() + msg.getSecondNumber()))
                    .collect(Collectors.toList());

            requestList.clear();

            ResponseMessage invalidMessage = sums.remove(9);
            invalidMessage.setResult(invalidMessage.getResult() + 3);
            jmsTemplate.convertAndSend(RESPONSE_QUEUE, invalidMessage);

            log.info("Sending responses...");
            sums.forEach(msg -> jmsTemplate.convertAndSend(RESPONSE_QUEUE, msg));
        }
    }
}
