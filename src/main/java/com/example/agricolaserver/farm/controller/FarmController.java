package com.example.agricolaserver.farm.controller;

import com.example.agricolaserver.farm.domain.Farm;
import com.example.agricolaserver.farm.dto.GetFarmResponse;
import com.example.agricolaserver.farm.dto.FarmDTO;
import com.example.agricolaserver.farm.dto.UpdateFarmRequestDTO;
import com.example.agricolaserver.farm.repository.FarmRepository;
import com.example.agricolaserver.farm.service.FarmService;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/farm")
public class FarmController {
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;
    private final FarmService farmService;

    public FarmController(FarmRepository farmRepository, MemberRepository memberRepository, FarmService farmService) {
        this.farmRepository = farmRepository;
        this.memberRepository = memberRepository;
        this.farmService = farmService;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<FarmDTO>> getFarms(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
    
        List<Farm> farms = farmRepository.findByMember(member);
    
        if (farms.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        List<FarmDTO> farmDTOs = farms.stream()
                .map(farm -> FarmDTO.makeFarmDTO(farm))
                .collect(Collectors.toList());
    
        return ResponseEntity.ok(farmDTOs);
    }    

    @PutMapping("/farmId/{farmId}")
    public ResponseEntity<GetFarmResponse> createOrUpdateFarm(@PathVariable Long memberId, @RequestBody UpdateFarmRequestDTO updateFarmRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        Farm farm = farmService.updateFarm(member, updateFarmRequestDTO);
        return ResponseEntity.ok(GetFarmResponse.from(farm));
    }
}