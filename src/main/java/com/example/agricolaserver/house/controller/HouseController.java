package com.example.agricolaserver.house.controller;


import com.example.agricolaserver.house.domain.House;
import com.example.agricolaserver.house.dto.GetHouseResponse;
import com.example.agricolaserver.house.dto.HouseDTO;
import com.example.agricolaserver.house.dto.UpdateHouseRequestDTO;
import com.example.agricolaserver.house.repository.HouseRepository;
import com.example.agricolaserver.house.service.HouseService;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/house")
public class HouseController {
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;
    private final HouseService houseService;

    public HouseController(HouseRepository houseRepository, MemberRepository memberRepository, HouseService houseService) {
        this.houseRepository = houseRepository;
        this.memberRepository = memberRepository;
        this.houseService = houseService;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<HouseDTO>> getHouses(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        List<House> houses = houseRepository.findByMember(member);

        if (houses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<HouseDTO> houseDTOs = houses.stream()
                .map(HouseDTO::makeHouseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(houseDTOs);
    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity<GetHouseResponse> createOrUpdateHouse(@PathVariable Long memberId, @RequestBody UpdateHouseRequestDTO updateHouseRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        House house = houseService.updateHouse(member, updateHouseRequestDTO);
        return ResponseEntity.ok(GetHouseResponse.from(house));
    }
}