package com.example.agricolaserver.house.service;

import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.house.dto.UpdateHouseRequestDTO;
import com.example.agricolaserver.house.repository.HouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Transactional
    public House updateHouse(Long houseId, UpdateHouseRequestDTO updateHouseRequestDTO) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("House not found with id: " + houseId));

        if (updateHouseRequestDTO.getType() != null) {
            house.setType(updateHouseRequestDTO.getType());
        }
        if (updateHouseRequestDTO.getXy() != null) {
            house.setXy(updateHouseRequestDTO.getXy());
        }
        if (updateHouseRequestDTO.getStock_type() != null) {
            house.setStock_type(updateHouseRequestDTO.getStock_type());
        }

        return houseRepository.save(house);
    }
}


