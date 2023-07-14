package com.example.topic.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @JmsListener(destination = "${destination.queue}", containerFactory = "queueConnectionFactory")
    public void receive(String text) {
        System.out.println("Received from queue: " + text);
    }

}
