package com.example.agricolaserver.equipment.controller;

import com.example.agricolaserver.equipment.service.EquipmentService;
import com.example.agricolaserver.equipment.dto.EquipmentDTO;
import com.example.agricolaserver.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equ")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentEquipmentService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<EquipmentDTO>> getEquipmentsByMember(@PathVariable Member memberId) {
        List<EquipmentDTO> equipmentEquipments = equipmentEquipmentService.getEquipmentsByMember(memberId);
        return ResponseEntity.ok(equipmentEquipments);
    }
    
    @GetMapping("/member/{memberId}/equ/{cardNumber}")
    public ResponseEntity<EquipmentDTO> getEquipmentByCardNumber(@PathVariable Member memberId, @PathVariable Integer cardNumber) {
        List<EquipmentDTO> equipmentEquipments = equipmentEquipmentService.getEquipmentsByMember(memberId);
        if (cardNumber >= 1 && cardNumber <= equipmentEquipments.size()) {
            EquipmentDTO equipmentEquipment = equipmentEquipments.get(cardNumber - 1); // cardNumber를 0부터가 아니라 1부터 시작하도록 수정
            return ResponseEntity.ok(equipmentEquipment); // 해당 카드 번호의 주요 설비 정보 반환
        } else {
            return ResponseEntity.notFound().build(); // 유효하지 않은 카드 번호면 404 에러 반환
        }
    }
    
    
}