package com.example.agricolaserver.family.controller;
import com.example.agricolaserver.family.dto.GetPositionResponse;
import com.example.agricolaserver.family.dto.UpdatePositionRequest;
import com.example.agricolaserver.family.service.FamilyService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/family")
public class FamilyController {
    private final FamilyService familyService;

    @MessageMapping("room/{roomId}/family/position/update")
    @SendTo("/sub/room/{roomId}")
    public List<GetPositionResponse> updatePosition(@DestinationVariable Long roomId, List<UpdatePositionRequest> updatePositionRequest){
        familyService.updatePosition(updatePositionRequest);
        return familyService.getPosition(roomId);
    }
    @GetMapping("get/{roomId}")
    public List<GetPositionResponse> getPosition(@PathVariable Long roomId){
        return familyService.getPosition(roomId);
    }
}