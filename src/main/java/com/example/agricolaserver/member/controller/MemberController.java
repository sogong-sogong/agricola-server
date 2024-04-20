package com.example.agricolaserver.member.controller;

import com.example.agricolaserver.member.service.EntranceResponse;
import com.example.agricolaserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @MessageMapping("room/{roomId}")
    @SendTo("/sub/room/{roomId}")
    public EntranceResponse entrance(@DestinationVariable Long roomId){
        return memberService.entrance(roomId);
    }
}