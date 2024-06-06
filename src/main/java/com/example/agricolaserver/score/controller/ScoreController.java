package com.example.agricolaserver.score.controller;

import com.example.agricolaserver.score.dto.ScoreDTO;
import com.example.agricolaserver.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;
    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ScoreDTO> getScoreByMemberId(@PathVariable Long memberId) {
        logger.info("GET /score/member/{}", memberId);
        ResponseEntity<ScoreDTO> response = scoreService.calculateScore(memberId);
        logger.info("Response: {}", response);
        return response;
    }
}