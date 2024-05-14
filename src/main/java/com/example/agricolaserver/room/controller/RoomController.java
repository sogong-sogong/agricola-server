package com.example.agricolaserver.room.controller;

import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.example.agricolaserver.room.dto.GetRoomDTO;
import com.example.agricolaserver.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping(path = "create")
    public ResponseEntity<CreateRoomDTO> createRoom(){

        return roomService.createRoom();
    }
    @GetMapping(path="all")
    public ResponseEntity<List<GetRoomDTO>> getAllRoom(){
        return roomService.getAllRoom();
    }
    private final static String ROOM_EXCHANGE_NAME = "room.exchange";
    private final static String ROOM_QUEUE_NAME = "room.queue";
    @MessageMapping("room.enter.{roomId}")
    @SendTo("/sub/room.{roomId}")
    public EntranceResponse entrance(@DestinationVariable Long roomId, @Payload  EntranceRequest entranceRequest){
//    @MessageMapping("room.enter.{roomId}")
        System.out.println("hello");
        rabbitTemplate.convertAndSend(ROOM_EXCHANGE_NAME,"enter.room."+roomId,entranceRequest);
        return roomService.entrance(roomId,entranceRequest);
    }
}