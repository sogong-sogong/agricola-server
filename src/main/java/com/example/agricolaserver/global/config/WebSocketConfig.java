package com.example.agricolaserver.global.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")   //SockJS 연결 주소
                .setAllowedOriginPatterns("*"); // 허용하는 도메인 주소 (일단 모두 허용으로 바꾸었다.)

    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher(".")); // URL을 / -> .으로
        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableStompBrokerRelay("/queue","/topic","/exchange","/amq/queue")
                .setRelayHost(host)
                .setRelayPort(61613)
                .setSystemLogin(username)
                .setSystemPasscode(password)
                .setClientLogin(username)
                .setClientPasscode(password);
    }
}