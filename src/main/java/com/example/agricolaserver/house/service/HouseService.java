package com.example.agricolaserver.house.service;

import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.house.dto.UpdateHouseRequestDTO;
import com.example.agricolaserver.house.repository.HouseRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    // 나무 집 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countWoodHouseByMember(Member member) {
        List<House> houses = houseRepository.findByMember(member);
        int WoodCount = 0;
        for (House house : houses) {
            if (house.getType() == "wood") {
                WoodCount++;
            }
        }
        return WoodCount;
    }

    // 흙 집 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countMudHouseByMember(Member member) {
        List<House> houses = houseRepository.findByMember(member);
        int MudCount = 0;
        for (House house : houses) {
            if (house.getType() == "mud") {
                MudCount++;
            }
        }
        return MudCount;
    }

    // 돌 집 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countStoneHouseByMember(Member member) {
        List<House> houses = houseRepository.findByMember(member);
        int StoneCount = 0;
        for (House house : houses) {
            if (house.getType() == "stone") {
                StoneCount++;
            }
        }
        return StoneCount;
    }
}


