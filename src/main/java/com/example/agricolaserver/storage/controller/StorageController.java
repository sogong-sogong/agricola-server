package com.example.agricolaserver.storage.controller;


import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.member.repository.MemberRepository;
import com.example.agricolaserver.storage.domain.Storage;
import com.example.agricolaserver.storage.service.StorageService;
import com.example.agricolaserver.storage.dto.GetStorageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{memberId}")
    public ResponseEntity<Storage> updateStorage(@PathVariable Long memberId,
                                                 @RequestParam(required = false) Integer wood,
                                                 @RequestParam(required = false) Integer clay,
                                                 @RequestParam(required = false) Integer stone,
                                                 @RequestParam(required = false) Integer weed,
                                                 @RequestParam(required = false) Integer grain,
                                                 @RequestParam(required = false) Integer vegetable,
                                                 @RequestParam(required = false) Integer food,
                                                 @RequestParam(required = false) Integer sheep,
                                                 @RequestParam(required = false) Integer pig,
                                                 @RequestParam(required = false) Integer cow,
                                                 @RequestParam(required = false) Integer family,
                                                 @RequestParam(required = false) Integer fence,
                                                 @RequestParam(required = false) Integer cowshed) {
        Storage updatedStorage = storageService.updateStorage(memberId, wood, clay, stone, weed, grain, vegetable, food, sheep, pig, cow, family, fence, cowshed);
        return ResponseEntity.ok(updatedStorage);
    }
}
