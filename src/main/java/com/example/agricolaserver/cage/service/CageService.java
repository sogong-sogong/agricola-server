package com.example.agricolaserver.cage.service;

import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.cage.dto.UpdateCageRequestDTO;
import com.example.agricolaserver.cage.repository.CageRepository;
import com.example.agricolaserver.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    // 우리 종류별 점수 반환 메소드 (type = 0, 울타리만 있는 우리)
    @Transactional(readOnly = true)
    public int countCages0ByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int type0Count = 0;
        for (Cage cage : cages) {
            if (cage.getType() == 0) {
                type0Count++;
            }
        }
        return type0Count;
    }

    // 우리 종류별 점수 반환 메소드 (type = 1, 외양간만 있는 우리)
    @Transactional(readOnly = true)
    public int countCages1ByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int type1Count = 0;
        for (Cage cage : cages) {
            if (cage.getType() == 1) {
                type1Count++;
            }
        }
        return type1Count;
     }

    // 우리 종류별 점수 반환 메소드 (type = 2, 울타리+외양간이 있는 우리)
    @Transactional(readOnly = true)
    public int countCages2ByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int type2Count = 0;
        for (Cage cage : cages) {
            if (cage.getType() == 2) {
                type2Count++;
            }
        }
        return type2Count;
    }

    // 양 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countSheepByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int SheepCount = 0;
        for (Cage cage : cages) {
            if (cage.getStock() == 0) {
                SheepCount =+ cage.getStock_cnt();
            }
        }
        return SheepCount;
    }

    // 돼지 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countPigByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int PigCount = 0;
        for (Cage cage : cages) {
            if (cage.getStock() == 1) {
                PigCount =+ cage.getStock_cnt();
            }
        }
        return PigCount;
    }

    // 소 점수 반환 메소드
    @Transactional(readOnly = true)
    public int countCowByMember(Member member) {
        List<Cage> cages = cageRepository.findByMember(member);
        int CowCount = 0;
        for (Cage cage : cages) {
            if (cage.getStock() == 2) {
                CowCount =+ cage.getStock_cnt();
            }
        }
        return CowCount;
    }
}


