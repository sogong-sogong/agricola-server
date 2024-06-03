package com.example.agricolaserver.round.controller;
import com.example.agricolaserver.boardresources.service.BoardResourcesService;
import com.example.agricolaserver.round.dto.RoundDTO;
import com.example.agricolaserver.round.service.UpdateRoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/round")
public class RoundController {
    private final UpdateRoundService updateRoundService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final BoardResourcesService boardResourcesService;

    @MessageMapping("room/{roomId}/round/update")
    @SendTo("/sub/room/{roomId}")
    public void updateRound(@DestinationVariable Long roomId){
        List<RoundDTO> roundUpdateDTOS = updateRoundService.updateRound(roomId);
        if(roundUpdateDTOS.size()==1){
            simpMessagingTemplate.convertAndSend("/sub/room/"+roomId, new List[]{roundUpdateDTOS, boardResourcesService.getResources(roomId)});
        }
        else
            simpMessagingTemplate.convertAndSend("/sub/room/"+roomId,new List[]{roundUpdateDTOS, boardResourcesService.updateResources(roomId)});
    }

    @GetMapping("/open/room/{roomId}")
    public List<RoundDTO> getOpenRounds(@PathVariable Long roomId) {
        return updateRoundService.getRound(roomId);
//        return updateRoundService.getOpenRounds(roomId);
    }
}