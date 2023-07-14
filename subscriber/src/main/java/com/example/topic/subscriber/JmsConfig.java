package com.example.topic.subscriber;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

    @Bean
    public ActiveMQConnectionFactory artemisConnectionFactory(@Value("${spring.activemq.broker-url}") String brokerUrl,
                                                              @Value("${spring.activemq.user}") String user,
                                                              @Value("${spring.activemq.password}") String password) {
        return new ActiveMQConnectionFactory(brokerUrl, user, password);
    }

    @Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(@Qualifier("artemisConnectionFactory") ActiveMQConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicConnectionFactory(@Qualifier("artemisConnectionFactory") ActiveMQConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

 /*   @Bean("publicSubscriberJmsTemplate")
    public JmsTemplate publicSubscriberJmsTemplate(ActiveMQConnectionFactory artemisConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(artemisConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }*/

}
