package com.example.topic.publisher;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
public class JmsConfig {

    @Bean
    public DynamicDestinationResolver destinationResolver() {
        return new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                if (destinationName.endsWith(".TOPIC")) {
                    pubSubDomain = true;
                }
                return super.resolveDestinationName(session, destinationName, pubSubDomain);
            }
        };
    }

    @Bean
    public ActiveMQConnectionFactory artemisConnectionFactory(@Value("${spring.activemq.broker-url}") String brokerUrl,
                                                              @Value("${spring.activemq.password}") String user,
                                                              @Value("${spring.activemq.user}") String password) {
        return new ActiveMQConnectionFactory(brokerUrl, user, password);
    }

    /*
    *
    * JMS TEMPLATE FOR QUEUE
    * */
    @Bean("artemisJmsTemplate")
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory artemisConnectionFactory) {
        return new JmsTemplate(artemisConnectionFactory);
    }

    /*
     *
     * JMS TEMPLATE FOR TOPIC
     * */
    @Bean("publishSubscriberJmsTemplate")
    public JmsTemplate publicSubscriberJmsTemplate(ActiveMQConnectionFactory artemisConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(artemisConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
