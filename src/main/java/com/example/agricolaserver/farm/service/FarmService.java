package com.example.agricolaserver.farm.service;

import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.farm.dto.UpdateFarmRequestDTO;
import com.example.agricolaserver.farm.repository.FarmRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {
    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Transactional
    public Farm updateFarm(Member member, UpdateFarmRequestDTO updateFarmRequestDTO) {
        Optional<Farm> existingFarm = Optional.empty();

        if (updateFarmRequestDTO.getId() != null) {
            existingFarm = farmRepository.findById(updateFarmRequestDTO.getId());
        }
        
        Farm farm;
        if (existingFarm.isPresent()) {
            farm = existingFarm.get();
            if (updateFarmRequestDTO.getType() != null) {
                farm.setType(updateFarmRequestDTO.getType());
            }
            if (updateFarmRequestDTO.getXy() != null) {
                farm.setXy(updateFarmRequestDTO.getXy());
            }
            if (updateFarmRequestDTO.getCrop() != null) {
                farm.setCrop(updateFarmRequestDTO.getCrop());
            }
        } else {
            farm = Farm.builder()
                   .member(member)
                   .type(updateFarmRequestDTO.getType())
                   .xy(updateFarmRequestDTO.getXy())
                   .crop(updateFarmRequestDTO.getCrop())
                   .build();
        }

        return farmRepository.save(farm);
    }

    @Transactional(readOnly = true)
    public int countFarmsByMember(Member member) {
        List<Farm> farms = farmRepository.findByMember(member);
        return farms.size();
    }
}
