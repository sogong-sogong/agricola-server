package com.example.agricolaserver.room.controller;

import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.GetRoomDTO;
import com.example.agricolaserver.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping(path = "create")
    public ResponseEntity<CreateRoomDTO> createRoom(){

        return roomService.createRoom();
    }
    @GetMapping(path="all")
    public ResponseEntity<List<GetRoomDTO>> getAllRoom(){
        return roomService.getAllRoom();
    }
}