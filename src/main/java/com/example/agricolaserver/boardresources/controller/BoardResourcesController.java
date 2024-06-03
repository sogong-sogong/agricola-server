package com.example.agricolaserver.boardresources.controller;

import com.example.agricolaserver.boardresources.dto.BoardResourcesDTO;
import com.example.agricolaserver.boardresources.service.BoardResourcesService;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.example.agricolaserver.round.service.UpdateRoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/boardresources")
public class BoardResourcesController {
    private final BoardResourcesService boardResourcesService;

    @GetMapping(path = "get/{roomId}")
    public ResponseEntity<List<BoardResourcesDTO>> getBoardResources(@PathVariable Long roomId) {
        return new ResponseEntity<>(boardResourcesService.getResources(roomId), HttpStatus.OK);
    }
}