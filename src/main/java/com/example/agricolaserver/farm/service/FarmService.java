package com.example.agricolaserver.farm.service;

import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.farm.dto.UpdateFarmRequestDTO;
import com.example.agricolaserver.farm.repository.FarmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FarmService {
    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Transactional
    public Farm updateFarm(Long farmId, UpdateFarmRequestDTO updateFarmRequestDTO) {
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Farm not found with id: " + farmId));

        if (updateFarmRequestDTO.getType() != null) {
            farm.setType(updateFarmRequestDTO.getType());
        }
        if (updateFarmRequestDTO.getXy() != null) {
            farm.setXy(updateFarmRequestDTO.getXy());
        }
        if (updateFarmRequestDTO.getCrop() != null) {
            farm.setCrop(updateFarmRequestDTO.getCrop());
        }

        return farmRepository.save(farm);
    }
}


