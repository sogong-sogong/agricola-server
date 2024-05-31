package com.example.agricolaserver.member.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.member.dto.CardCountDTO;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final StorageRepository storageRepository;

    public ResponseEntity<CreateMemberDTO> createMember() {
        try {
            Member member = Member.builder().build();
            memberRepository.save(member);
            return new ResponseEntity<>(new CreateMemberDTO(member.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateMemberDTO(null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CardCountDTO> getCardCountsByMemberId(Long memberId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            CardCountDTO cardCountDTO = new CardCountDTO(
                member.getOpenJob() != null ? member.getOpenJob() : 0,
                member.getHiddenJob() != null ? member.getHiddenJob() : 0,
                member.getEquipment() != null ? member.getEquipment() : 0,
                member.getOpenAuxiliary() != null ? member.getOpenAuxiliary() : 0,
                member.getHiddenAuxiliary() != null ? member.getHiddenAuxiliary() : 0
            );
            return new ResponseEntity<>(cardCountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
