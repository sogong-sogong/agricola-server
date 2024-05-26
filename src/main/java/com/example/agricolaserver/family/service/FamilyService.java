package com.example.agricolaserver.family.service;
import com.example.agricolaserver.family.domain.Family;
import com.example.agricolaserver.family.dto.GetPositionResponse;
import com.example.agricolaserver.family.dto.UpdatePositionRequest;
import com.example.agricolaserver.family.repository.FamilyRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    public void updatePosition(List<UpdatePositionRequest> updatePositionRequest){
        for(UpdatePositionRequest familyMember : updatePositionRequest){
            Family family = familyRepository.findById(familyMember.id()).orElseThrow(
                    () -> new RuntimeException("Family not found")
            );
            family.updatePosition(familyMember.xy());
            familyRepository.save(family);
        }
    }
    public List<GetPositionResponse> getPosition(Long roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(
                ()-> new RuntimeException("Room not found")
        );
        List<Member> members = memberRepository.findAllByRoom(room);
        List<GetPositionResponse> getPositionResponses = new ArrayList<>();
        for(Member member:members){
            List<Family> families = familyRepository.findAllByMember(member);
            List<GetPositionResponse.family> familyList = new ArrayList<>();
            for(Family family:families){
                familyList.add(new GetPositionResponse.family(family.getId(),family.getXy()));
            }
            getPositionResponses.add(new GetPositionResponse(member.getId(),familyList));
        }
        return getPositionResponses;
    }
}