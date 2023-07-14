package com.example.topic.publisher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

    private final JmsTemplate jmsTemplate;

    public QueueProducer(@Qualifier("artemisJmsTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Value("${destination.queue}")
    String queue;

    public void send(String text) {
        System.out.println("sent to queue: "+ text);
        jmsTemplate.convertAndSend(queue, text);
    }

}
