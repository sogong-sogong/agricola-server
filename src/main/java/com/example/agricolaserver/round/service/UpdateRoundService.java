package com.example.agricolaserver.round.service;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.round.domain.Round;
import com.example.agricolaserver.round.dto.RoundUpdateDTO;
import com.example.agricolaserver.round.repository.RoundRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateRoundService {
    private final RoundRepository roundRepository;
    private final RoomRepository roomRepository;
    public List<RoundUpdateDTO> updateRound(Long roomId){
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Room room = optionalRoom.orElseThrow(() -> new RuntimeException("Room not found"));
        room.updateRound(); //오픈된 마지막 라운드 카드 순서 저장
        roomRepository.save(room);
        Round round = roundRepository.findByRoomAndOrderNumber(room,room.getRound());
        round.open();
        roundRepository.save(round);
        List<Round> rounds = roundRepository.findByRoomAndOpen(room,true);
        List<RoundUpdateDTO> roundUpdateDTOList = new ArrayList<>();
        rounds.forEach(value->{
            roundUpdateDTOList.add(new RoundUpdateDTO(value.getTitle()));
        });
        return roundUpdateDTOList;
    }

    public List<RoundUpdateDTO> getOpenRounds(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        List<Round> openRounds = roundRepository.findByRoomAndOpen(room, true);
        List<RoundUpdateDTO> openRoundDTOs = new ArrayList<>();
        openRounds.forEach(round -> openRoundDTOs.add(new RoundUpdateDTO(round.getTitle())));
        return openRoundDTOs;
    }
}