package com.example.agricolaserver;

import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomEntranceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
//    protected StompSession stompSession;
//    private final WebSocketStompClient webSocketStompClient;
//
//    static final String WEBSOCKET_URI = "ws://localhost:";
//    private final BlockingQueue<EntranceResponse> responseQueue = new LinkedBlockingQueue<>();
//    public RoomEntranceTest(){
//        this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransport()));
//        this.webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
//    }
    private List<Transport> createTransport() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }
    @DisplayName("멤버 생성 테스트")
    @Test
    void createMember(){
        String url = "http://localhost:"+port+"/member/create";
        ResponseEntity<CreateMemberDTO> response = restTemplate.getForEntity(url,CreateMemberDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).memberId()).isEqualTo(1L);
    }
    @DisplayName("룸 생성 테스트")
    @Test
    void createRoom(){
        String url = "http://localhost:"+ port +"/room/create";
        ResponseEntity<CreateRoomDTO> response = restTemplate.getForEntity(url,CreateRoomDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).roomId()).isEqualTo(1L);
    }
//    @DisplayName("룸 입장 테스트")
//    @Test
//    public void entranceRoom() throws Exception {
//        StompSession session = webSocketStompClient.connectAsync(
//                WEBSOCKET_URI+port+"/ws-stomp", new StompSessionHandlerAdapter() {
//                }
//        ).get();
//                .get(1, TimeUnit.SECONDS);
//        session.subscribe("/sub/room/1", new StompSessionHandlerAdapter() {
//        });
//        EntranceRequest entranceRequest = new EntranceRequest(1L);
//        session.send("/pub/room/1",entranceRequest);
//        EntranceResponse response = responseQueue.poll(5, TimeUnit.SECONDS);
//        System.out.println("되냐? 여기" + response);
//    }
}
