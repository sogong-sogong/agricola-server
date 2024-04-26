package com.example.agricolaserver.member.service;

import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.dto.CreateMemberDTO;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final StorageRepository storageRepository;
    public ResponseEntity<CreateMemberDTO> createMember(){
       try{
           Member member =  Member.builder().build();
           memberRepository.save(member);
           return new ResponseEntity<>(new CreateMemberDTO(member.getId()),HttpStatus.OK);
       }
       catch(Exception e){
           return new ResponseEntity<>(new CreateMemberDTO(null),HttpStatus.BAD_REQUEST);
        }
    }
}