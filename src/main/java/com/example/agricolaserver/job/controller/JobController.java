package com.example.agricolaserver.job.controller;

import com.example.agricolaserver.job.service.JobService;
import com.example.agricolaserver.job.dto.JobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobEquipmentService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<JobDTO>> getJobsByMemberId(@PathVariable Long memberId) {
        List<JobDTO> jobEquipments = jobEquipmentService.getJobsByMemberId(memberId);
        return ResponseEntity.ok(jobEquipments);
    }
    
    @GetMapping("/member/{memberId}/job/{cardNumber}")
    public ResponseEntity<JobDTO> getJobByCardNumber(@PathVariable Long memberId, @PathVariable Integer cardNumber) {
        List<JobDTO> jobEquipments = jobEquipmentService.getJobsByMemberId(memberId);
        if (cardNumber >= 1 && cardNumber <= jobEquipments.size()) {
            JobDTO jobEquipment = jobEquipments.get(cardNumber - 1); // cardNumber를 0부터가 아니라 1부터 시작하도록 수정
            return ResponseEntity.ok(jobEquipment); // 해당 카드 번호의 보조 장비 정보 반환
        } else {
            return ResponseEntity.notFound().build(); // 유효하지 않은 카드 번호면 404 에러 반환
        }
    }
    
    
}
