package com.example.agricolaserver.boardresources.service;
import com.example.agricolaserver.boardresources.domain.BoardResources;
import com.example.agricolaserver.boardresources.dto.BoardResourcesDTO;
import com.example.agricolaserver.boardresources.repository.BoardResourcesRepository;
import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.room.domain.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardResourcesService {
    private final BoardResourcesRepository boardResourcesRepository;
    private final CommonStorageRepository commonStorageRepository;

    public void initBoardResources(Room room){
        List<BoardResources> boardResourcesList = new ArrayList<>();
        boardResourcesList.add(BoardResources.builder().room(room).xy(16).number(1).build()); //나무
        boardResourcesList.add(BoardResources.builder().room(room).xy(17).number(2).build()); //나무
        boardResourcesList.add(BoardResources.builder().room(room).xy(19).number(2).build()); //흙
        boardResourcesList.add(BoardResources.builder().room(room).xy(21).number(1).build()); //식량
        boardResourcesList.add(BoardResources.builder().room(room).xy(28).number(3).build()); //흙
        boardResourcesList.add(BoardResources.builder().room(room).xy(29).number(1).build()); //훍
        boardResourcesList.add(BoardResources.builder().room(room).xy(30).number(1).build()); //갈대밭
        boardResourcesList.add(BoardResources.builder().room(room).xy(31).number(1).build()); //식량
        boardResourcesRepository.saveAll(boardResourcesList);
    }

    public List<BoardResourcesDTO> updateResources(Long roomId){
        CommonStorage commonStorage = commonStorageRepository.findByRoomId(roomId);
        List<BoardResources> boardResourcesList = new ArrayList<>();
        BoardResources boardResources;
        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 16);
        boardResources.setNumber(boardResources.getNumber()+1);
        boardResourcesList.add(boardResources);
        commonStorage.setWood(commonStorage.getWood()-1);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 17);
        boardResources.setNumber(boardResources.getNumber()+2);
        boardResourcesList.add(boardResources);
        commonStorage.setWood(commonStorage.getWood()-2);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 19);
        boardResources.setNumber(boardResources.getNumber()+2);
        boardResourcesList.add(boardResources);
        commonStorage.setClay(commonStorage.getClay()-2);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 21);
        boardResources.setNumber(boardResources.getNumber()+1);
        boardResourcesList.add(boardResources);
        commonStorage.setFood(commonStorage.getFood()-1);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 28);
        boardResources.setNumber(boardResources.getNumber()+3);
        boardResourcesList.add(boardResources);
        commonStorage.setClay(commonStorage.getClay()-3);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 29);
        boardResources.setNumber(boardResources.getNumber()+1);
        boardResourcesList.add(boardResources);
        commonStorage.setClay(commonStorage.getClay()-1);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 30); //갈대밭
        boardResources.setNumber(boardResources.getNumber()+1);
        boardResourcesList.add(boardResources);
        commonStorage.setWeed(commonStorage.getWeed()-1);

        boardResources = boardResourcesRepository.findByRoomIdAndXy(roomId, 31); //식량
        boardResources.setNumber(boardResources.getNumber()+1);
        boardResourcesList.add(boardResources);
        commonStorage.setFood(commonStorage.getFood()-1);

        boardResourcesRepository.saveAll(boardResourcesList);
        commonStorageRepository.save(commonStorage);
        return getResources(roomId);
    }
    public List<BoardResourcesDTO> getResources(Long roomId){
        List<BoardResources> boardResourcesList = boardResourcesRepository.findAllByRoomId(roomId);
        List<BoardResourcesDTO> getBoardResourcesDTOList = new ArrayList<>();
        for(BoardResources boardResources : boardResourcesList){
            getBoardResourcesDTOList.add(BoardResourcesDTO.makeBoardResourcesDTO(boardResources));
        }
        return getBoardResourcesDTOList;
    }
}