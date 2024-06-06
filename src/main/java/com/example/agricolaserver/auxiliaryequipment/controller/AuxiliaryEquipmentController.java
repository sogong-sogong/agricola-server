package com.example.agricolaserver.auxiliaryequipment.controller;

import com.example.agricolaserver.auxiliaryequipment.service.AuxiliaryEquipmentService;
import com.example.agricolaserver.auxiliaryequipment.dto.AuxiliaryEquipmentDTO;
import com.example.agricolaserver.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aux")
@RequiredArgsConstructor
public class AuxiliaryEquipmentController {
    private final AuxiliaryEquipmentService auxiliaryEquipmentService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AuxiliaryEquipmentDTO>> getAuxiliaryEquipmentsByMember(@PathVariable Member memberId) {
        List<AuxiliaryEquipmentDTO> auxiliaryEquipments = auxiliaryEquipmentService.getAuxiliaryEquipmentsByMember(memberId);
        return ResponseEntity.ok(auxiliaryEquipments);
    }
    
    @GetMapping("/member/{memberId}/aux/{cardNumber}")
    public ResponseEntity<AuxiliaryEquipmentDTO> getAuxiliaryEquipmentByCardNumber(@PathVariable Member memberId, @PathVariable Integer cardNumber) {
        List<AuxiliaryEquipmentDTO> auxiliaryEquipments = auxiliaryEquipmentService.getAuxiliaryEquipmentsByMember(memberId);
        if (cardNumber >= 1 && cardNumber <= auxiliaryEquipments.size()) {
            AuxiliaryEquipmentDTO auxiliaryEquipment = auxiliaryEquipments.get(cardNumber - 1); // cardNumber를 0부터가 아니라 1부터 시작하도록 수정
            return ResponseEntity.ok(auxiliaryEquipment); // 해당 카드 번호의 보조 장비 정보 반환
        } else {
            return ResponseEntity.notFound().build(); // 유효하지 않은 카드 번호면 404 에러 반환
        }
    }
}
