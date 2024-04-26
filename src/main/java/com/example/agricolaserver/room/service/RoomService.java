package com.example.agricolaserver.room.service;
import com.example.agricolaserver.common.domain.Common;
import com.example.agricolaserver.common.repository.CommonRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.EntranceRequest;
import com.example.agricolaserver.room.dto.EntranceResponse;
import com.example.agricolaserver.room.dto.GetRoomDTO;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.round.service.RoundService;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoundService roundService;
    private final CommonRepository commonRepository;
    private final MemberRepository memberRepository;
    private final StorageRepository storageRepository;
    public ResponseEntity<CreateRoomDTO> createRoom(){
        try {
            Room room  = Room.builder().number(0).build();
            roomRepository.save(room); //룸 생성
            roundService.initRound(room); //라운드카드 초기화
            Common common = Common.builder().roomId(room).build(); //공동창고 초기화
            commonRepository.save(common);
            return new ResponseEntity<>(new CreateRoomDTO(room.getId()), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(new CreateRoomDTO(null), HttpStatus.BAD_REQUEST);
        }
    } //createRoom 이후 계정 입장 전 다른 사용자가 getAllRoom을 호출해 들어갈 시
    public ResponseEntity<List<GetRoomDTO>> getAllRoom(){
        try{
            List<Room> rooms = roomRepository.findAll();
            List<GetRoomDTO> allRoom = new ArrayList<>();
            for(Room room:rooms){
                allRoom.add(new GetRoomDTO(room.getId(),room.getNumber()));
            };
            return new ResponseEntity<>(allRoom, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    public EntranceResponse entrance(Long roomId, EntranceRequest entranceRequest) throws MessageDeliveryException {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
            if(optionalRoom.isEmpty() || optionalRoom.get().getNumber()>=4 || memberRepository.findById(entranceRequest.memberId()).isEmpty()){
                throw new MessageDeliveryException("방 번호나 멤버 id를 확인하세요.");
            }
            else if(memberRepository.findById(entranceRequest.memberId()).get().getRoom()==null){ //멤버의 게임방 속성이 null이면
                Room room = optionalRoom.get();
                room = Room.builder().id(roomId).number(room.getNumber()+1).build(); //방 인원 변경
                roomRepository.save(room);
                Member member = memberRepository.findById(entranceRequest.memberId()).get();
                member.setRoom(room); //멤버의 room,number 설정
                member.setNumber(room.getNumber());
                memberRepository.save(member);
                Storage storage = Storage.builder().memberId(member).build(); //멤버 자원 초기화
                storageRepository.save(storage);
                return new EntranceResponse(member.getId(),member.getNumber());
            }
            else{
                throw new MessageDeliveryException("이미 게임방에 입장한 멤버입니다.");
            }
    }
}