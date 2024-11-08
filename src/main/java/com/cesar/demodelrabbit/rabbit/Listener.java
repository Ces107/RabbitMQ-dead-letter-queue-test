package com.cesar.demodelrabbit.rabbit;

import com.cesar.demodelrabbit.DTO;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @RabbitListener(queues = "app.queue.Patata", containerFactory = "listenerContainerFactory")
    public void receiveMessage(DTO dto, Channel channel, Message message) throws Exception {
        if (dto.getTitle().equals("error")) {
            System.err.println("Error en el mensaje: " + dto);
            throw new RuntimeException("Error");

        }

        System.out.println("Mensaje recibido: " + dto);

    }
}
