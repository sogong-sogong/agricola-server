package com.example.agricolaserver.cage.service;

import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.cage.dto.UpdateCageRequestDTO;
import com.example.agricolaserver.cage.repository.CageRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CageService {
    private final CageRepository cageRepository;

    public CageService(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }

    @Transactional
   public Cage updateCage(Member member, UpdateCageRequestDTO updateCageRequestDTO) {
        Optional<Cage> existingCage = Optional.empty();

        if (updateCageRequestDTO.getId() != null) {
            existingCage = cageRepository.findById(updateCageRequestDTO.getId());
        }

        Cage cage;
        if (existingCage.isPresent()) {
            cage = existingCage.get();
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
        } else {
            cage = Cage.builder()
                   .member(member)
                   .type(updateCageRequestDTO.getType())
                   .stock(updateCageRequestDTO.getStock())
                   .xy(updateCageRequestDTO.getXy())
                   .stock_cnt(updateCageRequestDTO.getStock_cnt())
                   .build();
        }

        return cageRepository.save(cage);
    }
}


