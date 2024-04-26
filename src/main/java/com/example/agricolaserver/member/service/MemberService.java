package com.example.agricolaserver.member.service;

import com.example.agricolaserver.global.ResponseStatus;
import com.example.agricolaserver.member.dto.EntranceDTO;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final StorageRepository storageRepository;
    public EntranceDTO entrance(Long roomId) throws MessageDeliveryException{
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        try{
            if(optionalRoom.isEmpty() || optionalRoom.get().getNumber()>=4){
                throw new Exception();
            }
            else{
                Room room = roomRepository.findById(roomId).orElseThrow(Exception::new);
                room = Room.builder().id(roomId).number(room.getNumber()+1).build(); //방 인원 변경
                roomRepository.save(room);
                Member member = Member.builder().roomId(room).number(room.getNumber()).build(); //멤버 생성
                memberRepository.save(member);
                Storage storage = Storage.builder().memberId(member).build();
                storageRepository.save(storage);
                return new EntranceDTO(ResponseStatus.OK,member.getId(),member.getNumber());
            }
        }
        catch(Exception e) {
            return new EntranceDTO(ResponseStatus.BAD_REQUEST + " : 존재하지 않은 방이거나 방 수용인원을 초과하였습니다.",null,null);
        }
    }
}