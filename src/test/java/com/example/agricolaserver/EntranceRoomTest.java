package com.example.agricolaserver;
import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntranceRoomTest {
    @LocalServerPort
    private int port; //테스트시 사용되는 포트
    @Autowired
    private WebSocketStompClient stompClient;
    @Autowired
    private ObjectMapper objectMapper; //JSON 변환기
    @Autowired
    private TestRestTemplate restTemplate;

    //WebSocketStompClient를 설정하는 메소드
    @Autowired
    public void setStompClient(WebSocketStompClient stompClient) {
        this.stompClient = stompClient;
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        this.stompClient.setMessageConverter(messageConverter);
        this.stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
    }
    @DisplayName("멤버 생성 테스트")
    @Order(1)
    @Test
    void createMember(){
        String url = "http://localhost:"+port+"/member/create";
        ResponseEntity<CreateMemberDTO> response = restTemplate.getForEntity(url,CreateMemberDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).memberId()).isEqualTo(1L); //memberId가 1인 member를 생성했는지 체크
    }
    @DisplayName("룸 생성 테스트")
    @Order(2)
    @Test
    void createRoom(){
        String url = "http://localhost:"+ port +"/room/create";
        ResponseEntity<CreateRoomDTO> response = restTemplate.getForEntity(url,CreateRoomDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).roomId()).isEqualTo(1L); //roomId가 1인 room을 생성했는지 체크
    }
    @DisplayName("방 입장 테스트")
    @Order(3)
    @Test
    public void testStompMessaging() throws Exception {
        String url = "ws://localhost:"+port+"/ws-stomp";
        StompSession session = stompClient.connectAsync(url, new StompSessionHandlerAdapter() {
        }).get(1, TimeUnit.SECONDS);

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);

        session.subscribe("/sub/room/1", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return byte[].class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {
                    String message = new String((byte[]) payload, StandardCharsets.UTF_8);
                    System.out.println("Received message: " + message);
                    blockingQueue.offer(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        EntranceRequest entranceRequest = new EntranceRequest(1L); //memberId가 1인 member가 입장
        session.send("/pub/room/1", entranceRequest);
        String message = blockingQueue.poll(5, TimeUnit.SECONDS);
        EntranceResponse entranceResponse = new EntranceResponse(1L,1,false);
        String expectedJsonResponse = objectMapper.writeValueAsString(List.of(entranceResponse));
        EntranceResponse entranceResponse2 = new EntranceResponse(1L,1,true);
        String expectedJsonResponse2 = objectMapper.writeValueAsString(List.of(entranceResponse2));
        boolean isSuccess = Objects.equals(message, expectedJsonResponse) || Objects.equals(message, expectedJsonResponse2);
        assertTrue(isSuccess, "member 1의 room 1 입장 성공");
        //memberId가 1인 member가 잘 입장했는지 체크
    }
}