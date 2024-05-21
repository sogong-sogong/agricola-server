package com.example.agricolaserver.member.controller;

import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.member.dto.CardCountDTO;
import com.example.agricolaserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping(path = "create")
    public ResponseEntity<CreateMemberDTO> createMember() {
        return memberService.createMember();
    }

    @GetMapping(path = "/{memberId}/cardcount")
    public ResponseEntity<CardCountDTO> getCardCountsByMemberId(@PathVariable Long memberId) {
        return memberService.getCardCountsByMemberId(memberId);
    }
}
