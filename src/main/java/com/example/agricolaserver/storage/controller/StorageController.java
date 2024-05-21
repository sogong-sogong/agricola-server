package com.example.agricolaserver.storage.controller;


import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.service.StorageService;
import com.example.agricolaserver.storage.dto.GetStorageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;
    private final MemberRepository memberRepository;

    public StorageController(StorageService storageService, MemberRepository memberRepository) {
        this.storageService = storageService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/{memberID}")
    public ResponseEntity<GetStorageDTO> getStorageByMemberID(@PathVariable("memberID") Long memberId) {
        // memberId -> Member 객체
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Member 객체를 사용하여 Storage 데이터를 조회
        Storage storage = storageService.PersonalResources(member);

        if (storage != null) {
            GetStorageDTO storageDTO = GetStorageDTO.makeStorageDTO(storage);
            return ResponseEntity.ok(storageDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
