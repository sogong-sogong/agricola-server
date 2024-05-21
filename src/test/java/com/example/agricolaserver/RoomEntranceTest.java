package com.example.agricolaserver;

import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomEntranceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
//    protected StompSession stompSession;
//    private final String socketUrl;
//    private final WebSocketStompClient websocketClient;
//    public RoomEntranceTest() {
//        this.websocketClient = new WebSocketStompClient(new SockJsClient(createTransport()));
//        this.websocketClient.setMessageConverter(new MappingJackson2MessageConverter());
//        this.socketUrl = "ws://localhost:";
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

//    @BeforeEach
//    public void connect() throws ExecutionException, InterruptedException, TimeoutException {
//        this.stompSession = this.websocketClient
//                .connect(socketUrl + port + "/ws-stomp", new StompSessionHandlerAdapter() {})
//                .get(3, TimeUnit.SECONDS);
//    }
//    @AfterEach
//    public void disconnect() {
//        if (this.stompSession.isConnected()) {
//            this.stompSession.disconnect();
//        }
//    }
//    private List<Transport> createTransport() {
//        List<Transport> transports = new ArrayList<>(1);
//        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        return transports;
//    }
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
