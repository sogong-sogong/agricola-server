package com.example.agricolaserver.member.controller;
import com.example.agricolaserver.member.dto.EntranceDTO;
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
    public EntranceDTO entrance(@DestinationVariable Long roomId){
        return memberService.entrance(roomId);
    }
}
//중복해서 보낼시에도 여러 user가 생성되는 버그 존재 - 로그인 기능 추가해야만 해결 가능