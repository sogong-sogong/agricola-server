package com.example.agricolaserver.round.controller;
import com.example.agricolaserver.round.dto.RoundUpdateDTO;
import com.example.agricolaserver.round.service.UpdateRoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RoundController {
    private final UpdateRoundService updateRoundService;
    @MessageMapping("room/{roomId}/round/update")
    @SendTo("/sub/room/{roomId}")
    public List<RoundUpdateDTO> updateRound(@DestinationVariable Long roomId){
        return updateRoundService.updateRound(roomId);
    }
}