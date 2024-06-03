package com.example.agricolaserver.house.service;

import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.house.dto.UpdateHouseRequestDTO;
import com.example.agricolaserver.house.repository.HouseRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Transactional
    public House updateHouse(Member member, UpdateHouseRequestDTO updateHouseRequestDTO) {
        Optional<House> existingHouse = Optional.empty();

        if (updateHouseRequestDTO.getId() != null) {
            existingHouse = houseRepository.findById(updateHouseRequestDTO.getId());
        }

        House house;
        if (existingHouse.isPresent()) {
            house = existingHouse.get();
            if (updateHouseRequestDTO.getType() != null) {
                house.setType(updateHouseRequestDTO.getType());
            }
            if (updateHouseRequestDTO.getXy() != null) {
                house.setXy(updateHouseRequestDTO.getXy());
            }
            if (updateHouseRequestDTO.getStock_type() != null) {
                house.setStock_type(updateHouseRequestDTO.getStock_type());
            }
        } else {
            house = House.builder()
                    .member(member)
                    .type(updateHouseRequestDTO.getType())
                    .xy(updateHouseRequestDTO.getXy())
                    .stock_type(updateHouseRequestDTO.getStock_type())
                    .build();
        }

        return houseRepository.save(house);
    }
}


