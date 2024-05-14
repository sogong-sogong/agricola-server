package com.example.agricolaserver.global.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableRabbit
public class RabbitConfig {

    private static final String ROOM_QUEUE_NAME = "room.queue";
    private static final String ROOM_EXCHANGE_NAME = "room.exchange";
    private static final String ROUTING_KEY = "*.room.*";

    //Queue 등록
    @Bean
    public Queue queue() {
        return new Queue(ROOM_QUEUE_NAME, true);
    }

    //Exchange 등록
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(ROOM_EXCHANGE_NAME);
    }

    // Exchange와 Queue바인딩
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    // RabbitMQ와의 메시지 통신을 담당하는 클래스
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    // RabbitMQ와의 연결을 관리하는 클래스
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("52.78.128.39");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("agricola");
        return factory;
    }

    // 메시지를 JSON형식으로 직렬화하고 역직렬화하는데 사용되는 변환기
    // RabbitMQ 메시지를 JSON형식으로 보내고 받을 수 있음
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config) throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://admin:agricola@ec2host/%2F");
        return connectionFactory;
    }
}