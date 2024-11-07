package com.example.rabbitmqdemo.listener;


import com.cesar.demodelrabbit.DTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @RabbitListener(queues = "demo.queue")
    public void receiveMessage(DTO dto) {


        System.out.println("Mensaje recibido: " + dto);
    }
}