package com.cesar.demodelrabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private final RabbitTemplate rabbitTemplate;
    private static final String QUEUE_NAME = "demo.queue";

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(DTO message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        System.out.println("Mensaje enviado: " + message);
    }
}
