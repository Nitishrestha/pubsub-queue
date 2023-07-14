//package com.example.topic.subscriber;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.jms.JMSException;
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/queue/consumer")
//public class QueueConsumerController {
//
//    private final JmsTemplate jmsTemplate;
//    private final String destinationTopic;
//
//    public QueueConsumerController(@Qualifier("publicSubscriberJmsTemplate") JmsTemplate jmsTemplate,
//                                   @Value("${destination.topic}") String destinationTopic) {
//        this.jmsTemplate = jmsTemplate;
//        this.destinationTopic = destinationTopic;
//    }
//
//    @GetMapping(value = "/subscribe")
//    public ResponseEntity<String> subscribeQueue() throws JMSException {
//        return ResponseEntity.ok(Objects.requireNonNull(jmsTemplate.receive(destinationTopic)).getBody(String.class));
//    }
//
//    @JmsListener(destination = "${destination.queue}", containerFactory = "queueConnectionFactory")
//    public void receive(String text) {
//        System.out.println("Received from queue: " + text);
//    }
//
//
//}
