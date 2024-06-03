package com.example.agricolaserver.round.service;
import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import com.example.agricolaserver.round.domain.Round;
import com.example.agricolaserver.round.dto.RoundDTO;
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
    private final CommonStorageRepository commonStorageRepository;
    public List<RoundDTO> updateRound(Long roomId){
        CommonStorage commonStorage = commonStorageRepository.findByRoomId(roomId);
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Room room = optionalRoom.orElseThrow(() -> new RuntimeException("Room not found"));
        room.updateRound(); //오픈된 마지막 라운드 카드 순서 저장
        roomRepository.save(room);
        Round round = roundRepository.findByRoomAndOrderNumber(room,room.getRound());
        round.open();
        switch (round.getTitle()) {
            case "동부 채석장", "서부 채석장" -> {
                commonStorage.setStone(commonStorage.getStone() - 1);
                round.setNumber(1);
            }
            case "양 시장" -> {
                commonStorage.setStone(commonStorage.getSheep() - 1);
                round.setNumber(1);
            }
            case "돼지 시장" -> {
                commonStorage.setStone(commonStorage.getPig() - 1);
                round.setNumber(1);
            }
            case "소 시장" -> {
                commonStorage.setStone(commonStorage.getCow() - 1);
                round.setNumber(1);
            }
        }
        roundRepository.save(round);
        List<Round> rounds = roundRepository.findByRoomIdAndOpen(roomId,true);
//        List<RoundUpdateDTO> roundUpdateDTOList = new ArrayList<>();
        rounds.forEach(value->{
            if(value!=round){
                switch (value.getTitle()) {
                    case "동부 채석장", "서부 채석장" -> {
                        commonStorage.setStone(commonStorage.getStone() - 1);
                        value.setNumber(value.getNumber()+1);
                    }
                    case "양 시장" -> {
                        commonStorage.setStone(commonStorage.getSheep() - 1);
                        value.setNumber(value.getNumber()+1);
                    }
                    case "돼지 시장" -> {
                        commonStorage.setStone(commonStorage.getPig() - 1);
                        value.setNumber(value.getNumber()+1);
                    }
                    case "소 시장" -> {
                        commonStorage.setStone(commonStorage.getCow() - 1);
                        value.setNumber(value.getNumber()+1);
                    }
                }
            }
            commonStorageRepository.save(commonStorage);
        });
        return getRound(roomId);
    }
    public List<RoundDTO> getRound(Long roomId){
        List<Round> rounds = roundRepository.findByRoomIdAndOpen(roomId,true);
        List<RoundDTO> roundDTOList = new ArrayList<>();
        for(Round round : rounds){
            roundDTOList.add(new RoundDTO(round.getTitle(),round.getNumber()));
        }
        return roundDTOList;
    }

//    public List<RoundDTO> getOpenRounds(Long roomId) {
//        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
//        List<Round> openRounds = roundRepository.findByRoomAndOpen(room, true);
//        List<RoundDTO> openRoundDTOs = new ArrayList<>();
//        openRounds.forEach(round -> openRoundDTOs.add(new RoundUpdateDTO(round.getTitle())));
//        return openRoundDTOs;
//    }
}