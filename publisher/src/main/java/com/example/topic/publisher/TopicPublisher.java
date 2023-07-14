package com.example.topic.publisher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicPublisher {


    private final JmsTemplate jmsTemplate;

    public TopicPublisher(@Qualifier("publishSubscriberJmsTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Value("${destination.topic}")
    String topic;

    public void send(String text) {
        jmsTemplate.convertAndSend(topic, text);
        System.out.println("sent to topic: "+ text);
    }

}
