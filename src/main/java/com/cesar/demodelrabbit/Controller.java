package com.cesar.demodelrabbit;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class Controller {

    private final Sender messageSender;

    public Controller(Sender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping
    public String sendMessage(@RequestBody DTO message) {
        messageSender.sendMessage(message);

        return "Mensaje enviado";
    }
}
