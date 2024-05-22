package com.example.agricolaserver.global.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")   //SockJS 연결 주소
                .setAllowedOriginPatterns("*"); // 허용하는 도메인 주소 (일단 모두 허용으로 바꾸었다.)

    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //구독 url : 메시지 받을 때
        registry.enableSimpleBroker("/sub");
        // send url : 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
    }
    @Bean
    public WebSocketStompClient stompClient() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new StringMessageConverter());
        stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
        return stompClient;
    }
    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}