package com.example.agricolaserver.room.controller;

import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.GetStarterDTO;
import com.example.agricolaserver.room.dto.UpdateStarterRequest;
import com.example.agricolaserver.room.dto.UpdateStarterResponse;
import com.example.agricolaserver.room.service.RoomStarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/room/{roomId}")
public class RoomStarterController {
    private final RoomStarterService roomStarterService;

    @MessageMapping("room/{roomId}/starter/update")
    @SendTo("/sub/room/{roomId}")
    public UpdateStarterResponse updateStarter(@DestinationVariable Long roomId, @RequestBody UpdateStarterRequest updateStarterRequest) {

        return roomStarterService.updateStarter(roomId,updateStarterRequest);
    }
    @GetMapping(path = "starter/get")
    public GetStarterDTO getStarter(@PathVariable Long roomId){
        return roomStarterService.getStarter(roomId);
    }
}