package com.example.agricolaserver.cage.service;

import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.cage.dto.UpdateCageRequestDTO;
import com.example.agricolaserver.cage.repository.CageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CageService {
    private final CageRepository cageRepository;

    public CageService(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }

    @Transactional
    public Cage updateCage(Long cageId, UpdateCageRequestDTO updateCageRequestDTO) {
        Cage cage = cageRepository.findById(cageId)
                .orElseThrow(() -> new IllegalArgumentException("Cage not found with id: " + cageId));

        if (updateCageRequestDTO.getType() != null) {
            cage.setType(updateCageRequestDTO.getType());
        }
        if (updateCageRequestDTO.getStock() != null) {
            cage.setStock(updateCageRequestDTO.getStock());
        }
        if (updateCageRequestDTO.getXy() != null) {
            cage.setXy(updateCageRequestDTO.getXy());
        }
        if (updateCageRequestDTO.getStock_cnt() != null) {
            cage.setStock_cnt(updateCageRequestDTO.getStock_cnt());
        }
        if (updateCageRequestDTO.getField() != null) {
            cage.setField(updateCageRequestDTO.getField());
        }


        return cageRepository.save(cage);
    }
}


