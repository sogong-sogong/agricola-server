package com.example.agricolaserver.global.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp/")   //SockJS 연결 주소
                .setAllowedOriginPatterns("*"); // 허용하는 도메인 주소 (일단 모두 허용으로 바꾸었다.)

    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //구독 url : 메시지 받을 때
        registry.enableSimpleBroker("/sub");
        // send url : 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
    }
}