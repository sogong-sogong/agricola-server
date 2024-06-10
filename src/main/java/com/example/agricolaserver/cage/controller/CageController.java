package com.example.agricolaserver.cage.controller;


import com.example.agricolaserver.cage.domain.Cage;
import com.example.agricolaserver.cage.dto.GetCageResponse;
import com.example.agricolaserver.cage.dto.CageDTO;
import com.example.agricolaserver.cage.dto.UpdateCageRequestDTO;
import com.example.agricolaserver.cage.repository.CageRepository;
import com.example.agricolaserver.cage.service.CageService;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cage")
public class CageController {
    private final CageRepository cageRepository;
    private final MemberRepository memberRepository;
    private final CageService cageService;

    public CageController(CageRepository cageRepository, MemberRepository memberRepository, CageService cageService) {
        this.cageRepository = cageRepository;
        this.memberRepository = memberRepository;
        this.cageService = cageService;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<CageDTO>> getCages(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        List<Cage> cages = cageRepository.findByMember(member);

        if (cages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CageDTO> cageDTOs = cages.stream()
                .map(CageDTO::makeCageDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cageDTOs);
    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity<GetCageResponse> createOrUpdateCage(@PathVariable Long memberId, @RequestBody UpdateCageRequestDTO updateCageRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        Cage cage = cageService.updateCage(member, updateCageRequestDTO);
        return ResponseEntity.ok(GetCageResponse.from(cage));
    }
}