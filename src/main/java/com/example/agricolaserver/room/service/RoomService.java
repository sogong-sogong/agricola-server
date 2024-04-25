package com.example.agricolaserver.room.service;

import com.example.agricolaserver.room.dto.CreateRoomDTO;
import com.example.agricolaserver.room.dto.GetRoomDTO;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.round.service.RoundService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoundService roundService;
    public ResponseEntity<CreateRoomDTO> createRoom(){
        try {
            Room room  = Room.builder().number(0).build();
            roomRepository.save(room);
            roundService.initRound(room);
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
}
