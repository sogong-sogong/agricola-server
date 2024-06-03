package com.example.agricolaserver.commonstorage.service;

import com.example.agricolaserver.commonstorage.domain.CommonStorage;
import com.example.agricolaserver.commonstorage.dto.GetCommonStorageDTO;
import com.example.agricolaserver.commonstorage.dto.GetCommonStorageResponse;
import com.example.agricolaserver.commonstorage.dto.UpdateCommonStorageRequestDTO;
import com.example.agricolaserver.commonstorage.repository.CommonStorageRepository;
import com.example.agricolaserver.room.domain.Room;
import com.example.agricolaserver.room.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonStorageService {
    private final CommonStorageRepository commonStorageRepository;

    @Transactional
    public GetCommonStorageResponse getCommonStorage(Room roomId, UpdateCommonStorageRequestDTO requestDTO) {
        CommonStorage commonStorage = commonStorageRepository.findByRoom(roomId);
        if (commonStorage == null) {
            throw new IllegalArgumentException("해당 방의 공통 저장소를 찾을 수 없습니다.");
        }

        commonStorage.updatestorage(
                requestDTO.getWood() != null ? requestDTO.getWood() : commonStorage.getWood(),
                requestDTO.getClay() != null ? requestDTO.getClay() : commonStorage.getClay(),
                requestDTO.getStone() != null ? requestDTO.getStone() : commonStorage.getStone(),
                requestDTO.getWeed() != null ? requestDTO.getWeed() : commonStorage.getWeed(),
                requestDTO.getGrain() != null ? requestDTO.getGrain() : commonStorage.getGrain(),
                requestDTO.getVegetable() != null ? requestDTO.getVegetable() : commonStorage.getVegetable(),
                requestDTO.getFood() != null ? requestDTO.getFood() : commonStorage.getFood(),
                requestDTO.getSheep() != null ? requestDTO.getSheep() : commonStorage.getSheep(),
                requestDTO.getPig() != null ? requestDTO.getPig() : commonStorage.getPig(),
                requestDTO.getCow() != null ? requestDTO.getCow() : commonStorage.getCow()
        );

        commonStorageRepository.save(commonStorage);

        return toResponseDTO(commonStorage);
    }

    private GetCommonStorageResponse toResponseDTO(CommonStorage commonStorage) {
        GetCommonStorageResponse responseDTO = new GetCommonStorageResponse();
        responseDTO.setId(commonStorage.getId());
        responseDTO.setWood(commonStorage.getWood());
        responseDTO.setClay(commonStorage.getClay());
        responseDTO.setStone(commonStorage.getStone());
        responseDTO.setWeed(commonStorage.getWeed());
        responseDTO.setGrain(commonStorage.getGrain());
        responseDTO.setVegetable(commonStorage.getVegetable());
        responseDTO.setFood(commonStorage.getFood());
        responseDTO.setSheep(commonStorage.getSheep());
        responseDTO.setPig(commonStorage.getPig());
        responseDTO.setCow(commonStorage.getCow());
        return responseDTO;
    }
}
